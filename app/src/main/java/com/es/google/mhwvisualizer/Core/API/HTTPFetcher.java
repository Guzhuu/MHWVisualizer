package com.es.google.mhwvisualizer.Core.API;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.es.google.mhwvisualizer.R;
import com.es.google.mhwvisualizer.UI.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPFetcher extends AsyncTask<URL, Void, Boolean> {
    public static final String LOG_TAG = "HTTPFetcher";
    public static final String MHW_API = "https://mhw-db.com/weapons";

    public HTTPFetcher(MainActivity main){
        this.main = main;
    }

    @Override
    protected Boolean doInBackground(URL... urls)
    {
        InputStream is = null;
        boolean toret = false;

        try {
            // Check connectivty
            Log.d( LOG_TAG, " in doInBackground(): checking connectivity" );
            ConnectivityManager connMgr =
                    (ConnectivityManager)  this.main.getSystemService( Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            boolean connected = ( networkInfo != null && networkInfo.isConnected() );
            Log.d( LOG_TAG, " in doInBackground(), connected: " + connected );

            if ( !connected ) {
                this.main.setStatus( R.string.status_not_connected );
            } else {
                // Connection
                Log.d( LOG_TAG, " in doInBackground(): connecting" );
                HttpURLConnection conn = (HttpURLConnection) urls[ 0 ].openConnection();
                conn.setReadTimeout( 1000 /* milliseconds */ );
                conn.setConnectTimeout( 5000 /* milliseconds */ );
                conn.setRequestMethod( "GET" );
                conn.setDoInput( true );

                // Obtain the answer
                conn.connect();
                int responseCode = conn.getResponseCode();
                Log.d( LOG_TAG, String.format( " in doInBackground(): server response is: %s(%d)",
                        conn.getResponseMessage(),
                        responseCode ) );

                if ( responseCode == 200 ) {
                    this.responseParser = new ResponseParser( conn.getInputStream() );
                    toret = true;
                    Log.d( LOG_TAG, " in doInBackground(): finished" );
                    Log.i( LOG_TAG, " in doInBackground(): fetching ok" );
                } else {
                    Log.i( LOG_TAG, " in doInBackground(): fetching failed" );
                }
            }
        }
        catch(IOException exc) {
            Log.e( LOG_TAG, " in doInBackground(), connecting: " + exc.getMessage() );
        } finally {
            if ( is != null ) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e( LOG_TAG, " in doInBackGround(): error closing input stream" );
                }
            }
        }

        return toret;
    }

    @Override
    public void onPostExecute(Boolean result)
    {
        if ( result ) {
            this.main.setStatus( R.string.status_ok );
            this.main.srlMain.setRefreshing(false);
        } else {
            this.main.setStatus(R.string.status_not_ok);
            this.main.srlMain.setRefreshing(false);
        }

        return;
    }

    private MainActivity main;
    private ResponseParser responseParser;
}
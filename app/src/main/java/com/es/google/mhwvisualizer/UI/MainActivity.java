package com.es.google.mhwvisualizer.UI;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.es.google.mhwvisualizer.Core.API.HTTPFetcher;
import com.es.google.mhwvisualizer.Core.DB.DBManager;
import com.es.google.mhwvisualizer.Core.Gear.Gear;
import com.es.google.mhwvisualizer.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    /*La vista main será un menú para seleccionar el arbol de armas al que queremos ir, o bien a las que tenga el usuario
     *
     * Es decir, que debería ser un menu en un principio simple para testeo y luego mejorarlo en decoración, quizá un listview */
    public static final String LOGTAG = "MAIN";
    public Gear[] tiposDeArmas;
    public GearTypeButtonAdapter tiposDeArmasAdapter;
    public SwipeRefreshLayout srlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager.setContext(this.getApplicationContext());
        App.db = DBManager.getInstance();

        /*Crear los botones de lo que se quiera mostrar*/
        Gear BOWButton = new Gear(getResources().getString(R.string.BOW));
        BOWButton.setImagen("BOW");
        BOWButton.setTipo("Bow");
        Gear CBButton = new Gear(getResources().getString(R.string.CHARGEBLADE));
        CBButton.setImagen("CHARGEBLADE");
        CBButton.setTipo("ChargeBlade");
        Gear DBButton = new Gear(getResources().getString(R.string.DUALBLADES));
        DBButton.setImagen("DUALBLADES");
        DBButton.setTipo("DualBlades");
        Gear GSButton = new Gear(getResources().getString(R.string.GREATSWORD));
        GSButton.setImagen("GREATSWORD");
        GSButton.setTipo("GreatSword");
        Gear GLButton = new Gear(getResources().getString(R.string.GUNLANCE));
        GLButton.setImagen("GUNLANCE");
        GLButton.setTipo("GunLance");
        Gear HButton = new Gear(getResources().getString(R.string.HAMMER));
        HButton.setImagen("HAMMER");
        HButton.setTipo("Hammer");
        Gear HBButton = new Gear(getResources().getString(R.string.HEAVYBOWGUN));
        HBButton.setImagen("HEAVYBOWGUN");
        HBButton.setTipo("HeavyBowgun");
        Gear HHButton = new Gear(getResources().getString(R.string.HUNTINGHORN));
        HHButton.setImagen("HUNTINGHORN");
        HHButton.setTipo("HuntingHorn");
        Gear IGButton = new Gear(getResources().getString(R.string.INSECTGLAIVE));
        IGButton.setImagen("INSECTGLAIVE");
        IGButton.setTipo("InsectGlaive");
        /*
        Gear KIButton = new Gear(getResources().getString(R.string.KINECTINSECT));
        KIButton.setImagen("KINECTINSECT");
        KIButton.setTipo("KINECTINSECT");
        */
        Gear LButton = new Gear(getResources().getString(R.string.LANCE));
        LButton.setImagen("LANCE");
        LButton.setTipo("Lance");
        Gear LBButton = new Gear(getResources().getString(R.string.LIGHTBOWGUN));
        LBButton.setImagen("LIGHTBOWGUN");
        LBButton.setTipo("LightBowgun");
        Gear LSButton = new Gear(getResources().getString(R.string.LONGSWORD));
        LSButton.setImagen("LONGSWORD");
        LSButton.setTipo("LongSword");
        Gear SAButton = new Gear(getResources().getString(R.string.SWITCHAXE));
        SAButton.setImagen("SWITCHAXE");
        SAButton.setTipo("SwitchAxe");
        Gear SnSButton = new Gear(getResources().getString(R.string.SWORDNSHIELD));
        SnSButton.setImagen("SWORDANDSHIELD");
        SnSButton.setTipo("SwordandShield");


        this.tiposDeArmas = new Gear[]{BOWButton, CBButton, DBButton, GSButton, GLButton, HBButton, HButton, HHButton, IGButton, /*KIButton,*/
                LButton, LBButton, LSButton, SAButton, SnSButton};

        srlMain = findViewById(R.id.srlMain);
        srlMain.setOnRefreshListener(this);

        this.creaListaArmas();
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Cuando se vuelva aquí se vuelve a comprobar que no haya items nuevos, quizá no es buena idea (mejor actualizar items en propiedad o algo así)
        //this.checkForNewItems();
    }

    public void creaListaArmas(){
        final ListView lvOptions = findViewById(R.id.lvOptions);
        this.tiposDeArmasAdapter = new GearTypeButtonAdapter(this, this.tiposDeArmas);

        lvOptions.setAdapter(tiposDeArmasAdapter);
        lvOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent subArbol = new Intent(MainActivity.this, WeaponsActivity.class);

                Log.d(LOGTAG, tiposDeArmasAdapter.getItem(position).getTipo());
                subArbol.putExtra(App.WEAPON, tiposDeArmasAdapter.getItem(position).getTipo());

                MainActivity.this.startActivity( subArbol );
            }
        });
    }

    public void setStatus(int stringId){
        Toast.makeText(this, getResources().getString(stringId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        this.setStatus( R.string.status_init );

        try {
            new HTTPFetcher(MainActivity.this ).execute( new URL( HTTPFetcher.MHW_API ) );
        } catch(MalformedURLException e){
            Log.e( "URL ERROR", e.getMessage() );
            MainActivity.this.setStatus( R.string.status_incorrect_url );
        }
    }
}

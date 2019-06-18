package com.es.google.mhwvisualizer.UI;

import android.app.Application;

import com.es.google.mhwvisualizer.Core.DB.DBManager;
import com.es.google.mhwvisualizer.Core.Gear.Weapon;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Bow;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.ChargeBlade;

public class App extends Application {
    public static DBManager db;
    public static final String WEAPON = "WEAPON";
    public static Weapon weaponDetail = new ChargeBlade();

    @Override
    public void onCreate(){
        super.onCreate();
    }

    public void checkForNewItems(){
        /*Debe comprobar con la DB si hay nuevos items en lo recibido segun la API de MHW*/

    }
}

package com.es.google.mhwvisualizer.Core.API;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.es.google.mhwvisualizer.Core.DB.DBManager;
import com.es.google.mhwvisualizer.Core.Gear.Gear;
import com.es.google.mhwvisualizer.Core.Gear.Weapon;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Bow;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.ChargeBlade;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.DualBlades;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.GreatSword;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.GunLance;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Hammer;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.HeavyBowgun;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.HuntingHorn;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.InsectGlaive;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Lance;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.LightBowgun;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.LongSword;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.SwitchAxe;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.SwordandShield;
import com.es.google.mhwvisualizer.UI.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** ResponseParser interprets the answer obtained from the REST API */
public class ResponseParser {
    public static final String LOG_TAG = "ResponseParser";

    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String RARITY = "rarity";
    public static final String ATTACK = "attack";
    public static final String ATTACK_DISPLAY = "display";
    public static final String DEFENSE = "defense";
    public static final String ELDERSEAL = "elderseal";
    public static final String SLOTS = "slots";
    public static final String SLOTS_RANK = "rank";
    public static final String ELEMENT = "elements";
    public static final String ELEMENT_TYPE = "type";
    public static final String ELEMENT_DAMAGE = "damage";
    public static final String ELEMENT_HIDDEN = "hidden";
    public static final String UPGRADES = "crafting";
    public static final String UPGRADES_MEJORAS = "branches";
    public static final String IMGS = "assets";
    public static final String IMGS_ICON = "icon";
    public static final String IMGS_IMG = "image";
    public static final String SHARPNESS = "sharpness";
    public static final String SHARPNESS_RED = "red";
    public static final String SHARPNESS_ORANGE = "orange";
    public static final String SHARPNESS_YELLOW = "yellow";
    public static final String SHARPNESS_GREEN = "green";
    public static final String SHARPNESS_BLUE = "blue";
    public static final String SHARPNESS_WHITE = "white";
    public static final String COATINGS = "coatings";
    public static final String AFFINITY = "affinity";
    public static final String DEVIATION = "deviation";
    public static final String SPECIALAMMO = "specialAmmo";
    public static final String ATTRIBUTES = "attributes";
    public static final String PROYECTIL = "shellingType";
    public static final String BONUSINSECTO = "boostType";
    public static final String NOTES = "notes"; //No existe en la API

    public static final String TYPE_BOW = "bow";
    public static final String TYPE_GS = "great-sword";
    public static final String TYPE_DB = "dual-blades";
    public static final String TYPE_L = "lance";
    public static final String TYPE_CB = "charge-blade";
    public static final String TYPE_HB = "heavy-bowgun";
    public static final String TYPE_LG = "long-sword";
    public static final String TYPE_H = "hammer";
    public static final String TYPE_GL = "gunlance";
    public static final String TYPE_IG = "insect-glaive";
    public static final String TYPE_SnS = "sword-and-shield";
    public static final String TYPE_HH = "hunting-horn";
    public static final String TYPE_SA = "switch-axe";
    public static final String TYPE_LB = "light-bowgun";

    /** Creates a new ResponseParser, given the InputStream from the connection */
    public ResponseParser(InputStream is)
    {
        this.query( is );
    }

    private void query(InputStream is)
    {
        try {
            Log.d(LOG_TAG, " in doInBackground(): querying");
            JSONArray weapons = new JSONArray( getStringFromStream( is ) );
            Log.d(LOG_TAG, " in doInBackground(): content fetched: " + weapons.toString( 0 ));

            JSONObject weapon;
            Gear gear;
            DBManager db = App.db;
            for(int i = 0; i < weapons.length(); i++){
               Log.d(LOG_TAG, "Cogiendo arma...");
               weapon = weapons.getJSONObject(i);
               switch(weapon.getString(TYPE)){
                   case TYPE_BOW:
                       //TODO: No insert, pero check. El insert desde dentro de DBManager
                       db.guardarArma(this.parseBow(weapon));
                       break;
                   case TYPE_SnS:
                       db.guardarArma(this.parseSnS(weapon));
                       break;
                   case TYPE_SA:
                       db.guardarArma(this.parseSA(weapon));
                       break;
                   case TYPE_LG:
                       db.guardarArma(this.parseLS(weapon));
                       break;
                   case TYPE_LB:
                       db.guardarArma(this.parseLB(weapon));
                       break;
                   case TYPE_L:
                       db.guardarArma(this.parseL(weapon));
                       break;
                   case TYPE_IG:
                       db.guardarArma(this.parseIG(weapon));
                       break;
                   case TYPE_HH:
                       db.guardarArma(this.parseHH(weapon));
                       break;
                   case TYPE_HB:
                       db.guardarArma(this.parseHB(weapon));
                       break;
                   case TYPE_H:
                       db.guardarArma(this.parseH(weapon));
                       break;
                   case TYPE_GS:
                       db.guardarArma(this.parseGS(weapon));
                       break;
                   case TYPE_GL:
                       db.guardarArma(this.parseGL(weapon));
                       break;
                   case TYPE_DB:
                       db.guardarArma(this.parseDB(weapon));
                       break;
                   case TYPE_CB:
                       db.guardarArma(this.parseCB(weapon));
                       break;
                   default:
                       Log.d(LOG_TAG, "Tipo de arma no recogido " + weapon.getString(ID));
               }
            }
        } catch(JSONException exc) {
            Log.e( LOG_TAG, " in query(): " + exc.getMessage() );
        }
    }

    private String getStringFromStream(InputStream is)
    {
        BufferedReader reader = null;
        StringBuilder toret = new StringBuilder();
        String line;

        try {
            reader = new BufferedReader( new InputStreamReader( is ) );
            while( ( line = reader.readLine() ) != null ) {
                toret.append( line );
            }
        } catch (IOException e) {
            Log.e( LOG_TAG, " in getStringFromString(): error converting net input to string"  );
        }

        return toret.toString();
    }

    private Weapon parseWeapon(JSONObject weapon){
        Weapon gear = new Weapon();
        try {
            Log.i(LOG_TAG, "Weapon " + weapon.getString(ID));
            if (weapon.has(ID)) {
                Log.d(LOG_TAG, "Cogiendo " + ID + " de " + weapon.getInt(ID) + ": " + weapon.getInt(ID));
                gear.setID(weapon.getInt(ID));
            }
            if (weapon.has(NAME)) {
                Log.d(LOG_TAG, "Cogiendo " + NAME + " de " + weapon.getInt(ID) + ": " + weapon.getString(NAME));
                gear.setNombre(weapon.getString(NAME));
            }
            if (weapon.has(RARITY)) {
                Log.d(LOG_TAG, "Cogiendo " + RARITY + " de " + weapon.getInt(ID) + ": " + weapon.getInt(RARITY));
                gear.setRareza(weapon.getInt(RARITY));
            }
            if (weapon.has(ATTACK) && weapon.getJSONObject(ATTACK).has(ATTACK_DISPLAY)) {
                Log.d(LOG_TAG, "Cogiendo " + ATTACK + " de " + weapon.getInt(ID) + ": " + weapon.getJSONObject(ATTACK).getInt(ATTACK_DISPLAY));
                gear.setDanho(weapon.getJSONObject(ATTACK).getInt(ATTACK_DISPLAY));
            }
            if (weapon.has(DEFENSE)) {
                Log.d(LOG_TAG, "Cogiendo " + DEFENSE + " de " + weapon.getInt(ID) + ": " + weapon.getInt(DEFENSE));
                gear.setDefensa(weapon.getInt(DEFENSE));
            }
            if (weapon.has(ELEMENT)) {
                Log.d(LOG_TAG, "Cogiendo " + ELEMENT + " de " + weapon.getInt(ID));
                JSONArray array = weapon.getJSONArray(ELEMENT);
                for (int j = 0; j < array.length(); j++) {
                    JSONObject obj = array.getJSONObject(j);
                    if (j == 0 && obj.has(ELEMENT_DAMAGE) && obj.has(ELEMENT_HIDDEN) && obj.has(ELEMENT_TYPE)) {
                        gear.setElemento(obj.getString(ELEMENT_TYPE));
                        gear.setElementoCantidad(obj.getInt(ELEMENT_DAMAGE));
                        gear.setElementoOculto(obj.getBoolean(ELEMENT_HIDDEN));
                    }
                }
            }
            if (weapon.has(ATTRIBUTES)) {
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                if (attrs.has(ELDERSEAL)) {
                    Log.d(LOG_TAG, "Cogiendo " + ELDERSEAL + " de " + weapon.getInt(ID) + ": " + attrs.getString(ELDERSEAL));
                    gear.setSelloAncianos(attrs.getString(ELDERSEAL));
                }
                if (attrs.has(AFFINITY)) {
                    Log.d(LOG_TAG, "Cogiendo " + AFFINITY + " " + attrs.has(AFFINITY) + " de " + weapon.getInt(ID) + ": " + attrs.getInt(AFFINITY));
                    gear.setAfinidad(attrs.getInt(AFFINITY));
                }
            }
            if (weapon.has(UPGRADES)) {
                Log.d(LOG_TAG, "Cogiendo " + UPGRADES + " de " + weapon.getInt(ID));
                JSONArray mejoras = weapon.getJSONObject(UPGRADES).getJSONArray(UPGRADES_MEJORAS);
                List<Integer> upgrades = new LinkedList<>();
                for (int j = 0; j < mejoras.length(); j++) {
                    Log.d(LOG_TAG, "Cogiendo " + UPGRADES_MEJORAS + " de " + weapon.getInt(ID) + ": " + mejoras.getInt(j));
                    upgrades.add(mejoras.getInt(j));
                }
                Log.d(LOG_TAG, "Mejoras de " + weapon.getInt(ID) + ": " + upgrades.toString());
                gear.setMejoras(upgrades);
            }
            if (weapon.has(SLOTS)) {
                JSONArray huecos = weapon.getJSONArray(SLOTS);
                List<Integer> slots = new LinkedList<>();
                for (int j = 0; j < huecos.length(); j++) {
                    Log.d(LOG_TAG, "Cogiendo " + SLOTS + " de " + weapon.getInt(ID) + ": " + huecos.getJSONObject(j).getInt(SLOTS_RANK));
                    slots.add(huecos.getJSONObject(j).getInt(SLOTS_RANK));
                }
                gear.setHuecosJoyas(slots);
            }
            if (weapon.has(IMGS) && weapon.get(IMGS) != null) {
                JSONObject imgs = weapon.getJSONObject(IMGS);
                if (imgs.has(IMGS_ICON)) {
                    Log.d(LOG_TAG, "Cogiendo " + IMGS_ICON + " de " + weapon.getInt(ID) + ": " + imgs.getString(IMGS_ICON));
                    gear.setIcon(imgs.getString(IMGS_ICON));
                }
                if (imgs.has(IMGS_IMG)) {
                    Log.d(LOG_TAG, "Cogiendo " + IMGS_IMG + " de " + weapon.getInt(ID) + ": " + imgs.getString(IMGS_IMG));
                    gear.setImagen(imgs.getString(IMGS_IMG));
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear arma " + e.getMessage());
        }
        return gear;
    }

    private Map<Integer, Integer> parseSharpness(JSONObject weapon){
        Map<Integer, Integer> retorno = new HashMap<>();
        try {
            if (weapon.has(SHARPNESS)) {
                Log.d(LOG_TAG, "Cogiendo " + SHARPNESS + " de " + weapon.getInt(ID));
                JSONObject sharpness = weapon.getJSONObject(SHARPNESS);
                if(sharpness.has(SHARPNESS_RED)){
                    retorno.put(Weapon.AFILADOROJO, sharpness.getInt(SHARPNESS_RED));
                }
                if(sharpness.has(SHARPNESS_ORANGE)){
                    retorno.put(Weapon.AFILADONARANJA, sharpness.getInt(SHARPNESS_ORANGE));
                }
                if(sharpness.has(SHARPNESS_YELLOW)){
                    retorno.put(Weapon.AFILADOAMARILLO, sharpness.getInt(SHARPNESS_YELLOW));
                }
                if(sharpness.has(SHARPNESS_GREEN)){
                    retorno.put(Weapon.AFILADOVERDE, sharpness.getInt(SHARPNESS_GREEN));
                }
                if(sharpness.has(SHARPNESS_BLUE)){
                    retorno.put(Weapon.AFILADOAZUL, sharpness.getInt(SHARPNESS_BLUE));
                }
                if(sharpness.has(SHARPNESS_WHITE)){
                    retorno.put(Weapon.AFILADOBLANCO, sharpness.getInt(SHARPNESS_WHITE));
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear afilado " + e.toString());
        }
        return retorno;
    }

    private Bow parseBow(JSONObject weapon) {
        Bow gear = new Bow(parseWeapon(weapon));
        try {
            if (weapon.has(ATTRIBUTES)) {
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                if (attrs.has(COATINGS)) {
                    Log.d(LOG_TAG, "Cogiendo " + COATINGS + " de " + weapon.getInt(ID));
                    JSONArray coatings = attrs.getJSONArray(COATINGS);
                    List<String> coats = new LinkedList<>();
                    for (int j = 0; j < coatings.length(); j++) {
                        coats.add(coatings.getString(j));
                    }
                    gear.setRevestimientos(coats);
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear arco " + e.getMessage());
        }
        return gear;
    }

    private SwordandShield parseSnS(JSONObject weapon){
        SwordandShield gear = new SwordandShield(parseWeapon(weapon));
        try {
                gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private ChargeBlade parseCB(JSONObject weapon){
        ChargeBlade gear = new ChargeBlade(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private SwitchAxe parseSA(JSONObject weapon){
        SwitchAxe gear = new SwitchAxe(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private LongSword parseLS(JSONObject weapon){
        LongSword gear = new LongSword(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private GreatSword parseGS(JSONObject weapon){
        GreatSword gear = new GreatSword(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private Lance parseL(JSONObject weapon){
        Lance gear = new Lance(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private Hammer parseH(JSONObject weapon){
        Hammer gear = new Hammer(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private GunLance parseGL(JSONObject weapon){
        GunLance gear = new GunLance(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
            if(weapon.has(ATTRIBUTES)){
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                if (attrs.has(PROYECTIL)) {
                    Log.d(LOG_TAG, "Cogiendo " + PROYECTIL + " de " + weapon.getInt(ID) + ": " + attrs.getString(PROYECTIL));
                    String[] proyectil = attrs.getString(PROYECTIL).split(" ");

                    gear.setProyectil(proyectil[0]);
                    gear.setLvlProyectil(Character.getNumericValue(proyectil[1].charAt(2))); //Peligroso para el futuras actualizaciones
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private InsectGlaive parseIG(JSONObject weapon){
        InsectGlaive gear = new InsectGlaive(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
            if(weapon.has(ATTRIBUTES)){
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                if (attrs.has(BONUSINSECTO)) {
                    Log.d(LOG_TAG, "Cogiendo " + BONUSINSECTO + " de " + weapon.getInt(ID) + ": " + attrs.getString(BONUSINSECTO));

                    gear.setBonusInsecto(attrs.getString(BONUSINSECTO));
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear SwordandShield " + e.toString());
        }
        return gear;
    }

    private DualBlades parseDB(JSONObject weapon){
        DualBlades gear = new DualBlades(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
            if (weapon.has(ELEMENT) && weapon.getJSONArray(ELEMENT).length() >= 2) {
                Log.d(LOG_TAG, "Cogiendo elemento secundario  de " + weapon.getInt(ID));
                JSONArray array = weapon.getJSONArray(ELEMENT);
                for (int j = 1; j < array.length(); j++) {
                    JSONObject obj = array.getJSONObject(j);
                    if (j == 1 && obj.has(ELEMENT_DAMAGE) && obj.has(ELEMENT_HIDDEN) && obj.has(ELEMENT_TYPE)) {
                        gear.setElem2(obj.getString(ELEMENT_TYPE));
                        gear.setElem2cantidad(obj.getInt(ELEMENT_DAMAGE));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear DualBlades " + e.toString());
        }
        return gear;
    }

    private LightBowgun parseLB(JSONObject weapon) {
        LightBowgun gear = new LightBowgun(parseWeapon(weapon));
        try {
            if (weapon.has(ATTRIBUTES)) {
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                if (attrs.has(DEVIATION)) {
                    Log.d(LOG_TAG, "Cogiendo " + DEVIATION + " de " + weapon.getInt(ID));
                    gear.setDesvio(attrs.getString(DEVIATION));
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear LightBowgun " + e.getMessage());
        }
        return gear;
    }

    private HeavyBowgun parseHB(JSONObject weapon) {
        HeavyBowgun gear = new HeavyBowgun(parseWeapon(weapon));
        try {
            if (weapon.has(ATTRIBUTES)) {
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                if (attrs.has(DEVIATION)) {
                    Log.d(LOG_TAG, "Cogiendo " + DEVIATION + " de " + weapon.getInt(ID));
                    gear.setDesvio(attrs.getString(DEVIATION));
                }
                if (attrs.has(SPECIALAMMO)) {
                    Log.d(LOG_TAG, "Cogiendo " + SPECIALAMMO + " de " + weapon.getInt(ID));
                    gear.setMunicionEspecial(attrs.getString(SPECIALAMMO));
                }
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear LightBowgun " + e.getMessage());
        }
        return gear;
    }

    private HuntingHorn parseHH(JSONObject weapon){
        HuntingHorn gear = new HuntingHorn(parseWeapon(weapon));
        try {
            gear.setAfilado(parseSharpness(weapon));
            if (weapon.has(ATTRIBUTES)) {
                Log.d(LOG_TAG, "Cogiendo " + ATTRIBUTES + " de " + weapon.getInt(ID));
                JSONObject attrs = weapon.getJSONObject(ATTRIBUTES);
                /*
                if (attrs.has(NOTES) && attrs.get(NOTES) instanceof JSONArray) {
                    Log.d(LOG_TAG, "Cogiendo " + NOTES + " de " + weapon.getInt(ID));
                    JSONArray notas = attrs.getJSONArray(NOTES);
                    List<String> notasList = new LinkedList<>();

                    for(int i = 0; i < notas.length(); i++){
                        notasList.add(notas.getString(i));
                    }

                    gear.setNotas(notasList);
                }*/
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Fallo al parsear HuntingHorn " + e.toString());
        }
        return gear;
    }
}
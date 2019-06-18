package com.es.google.mhwvisualizer.Core.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
import com.es.google.mhwvisualizer.Core.Gear.Weapons.KinectInsect;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Lance;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.LightBowgun;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.LongSword;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.SwitchAxe;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.SwordandShield;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBManager extends SQLiteOpenHelper {
    //Singleton
    private static DBManager singleton;

    private static Boolean changeContext;
    private static Context context;

    private static final String DB_NOMBRE = "GEAR";
    private static int DB_VERSION = 1;

    private static final String WEAPON_TABLE = "WEAPON";
    private static final String BOW_TABLE = "BOW";
    private static final String CHARGEBLADE_TABLE = "CHARGEBLADE";
    private static final String DUALBLADES_TABLE = "DUALBLADES";
    private static final String GREATSWORD_TABLE = "GREATSWORD";
    private static final String GUNLANCE_TABLE = "GUNLANCE";
    private static final String HAMMER_TABLE = "HAMMER";
    private static final String HEAVYBOWGUN_TABLE = "HEAVYBOWGUN";
    private static final String HUNTINGHORN_TABLE = "HUNTINGHORN";
    private static final String HUNTINGHORNNOTES_TABLE = "HUNTINGHORNNOTES";
    private static final String INSECTGLAIVE_TABLE = "INSECTGLAIVE";
    private static final String LANCE_TABLE = "LANCE";
    private static final String LIGHTBOWGUN_TABLE = "LIGHTBOWGUN";
    private static final String LONGSWORD_TABLE = "LONGSWORD";
    private static final String SWITCHAXE_TABLE = "SWITCHAXE";
    private static final String SWORDANDSHIELD_TABLE = "SWORDANDSHIELD";
    private static final String KINECTINSECT_TABLE = "KINECTINSECT";
    private static final String MEJORAS_TABLE = "MEJORAS";
    private static final String HUECOS_TABLE = "HUECOS";

    private static String[] nombreTablas = new String[]{WEAPON_TABLE, BOW_TABLE, CHARGEBLADE_TABLE, DUALBLADES_TABLE, GREATSWORD_TABLE, GUNLANCE_TABLE, HAMMER_TABLE, HEAVYBOWGUN_TABLE,
            HUNTINGHORN_TABLE, HUNTINGHORNNOTES_TABLE, INSECTGLAIVE_TABLE, LANCE_TABLE, LIGHTBOWGUN_TABLE, LONGSWORD_TABLE, SWITCHAXE_TABLE,
            SWORDANDSHIELD_TABLE, KINECTINSECT_TABLE, MEJORAS_TABLE, HUECOS_TABLE};

    private static final String ID_COLUMN = "ID";
    private static final String IDarmabase_COLUMN = "ID_arma_base";
    private static final String IDarmamejora_COLUMN = "ID_arma_mejora";
    private static final String IDHH_COLUMN = "ID_HH";

    private static final String RAREZA_COLUMN = "rareza";
    private static final String DANHO_COLUMN = "danho";
    private static final String AFINIDAD_COLUMN = "afinidad";
    private static final String ELEMENTOCANTIDAD_COLUMN = "elemento_cantidad";
    private static final String ELEMENTO_COLUMN = "elemento";
    private static final String ELEMENTOOCULTO_COLUMN = "elemento_oculto";
    private static final String SELLOANCIANOS_COLUMN = "sello_ancianos";
    private static final String DEFENSA_COLUMN = "defensa";
    private static final String NOMBRE_COLUMN = "nombre";
    private static final String IMGENLACE_COLUMN = "img_enlace";
    private static final String ICONENLACE_COLUMN = "icon_enlace";

    private static final String REVESTIMIENTO_COLUMN = "revestimiento";
    private static final String HUECO_COLUMN = "hueco";
    private static final String DESVIO_COLUMN = "desvio";
    private static final String ELEMENTOSECUNDARIO_COLUMN = "elemento_secundario";
    private static final String ELEMENTOSECUNDARIOCANTIDAD_COLUMN = "elemento_secundario_cantidad";
    private static final String NOTA_COLUMN = "nota";
    private static final String PROYECTILTIPO_COLUMN = "proyectil_tipo";
    private static final String PROYECTILLVL_COLUMN = "proyectil_lvl";
    private static final String MUNESPECIAL_COLUMN = "municion_especial";
    private static final String BONUSINSECTO_COLUMN = "bonus_insecto";

    private static final String AFILADOROJO_COLUMN = "afilado_rojo";
    private static final String AFILADONARANJA_COLUMN = "afilado_naranja";
    private static final String AFILADOAMARILLO_COLUMN= "afilado_amarillo";
    private static final String AFILADOVERDE_COLUMN = "afilado_verde";
    private static final String AFILADOAZUL_COLUMN= "afilado_azul";
    private static final String AFILADOBLANCO_COLUMN = "afilado_blanco";

    //Otra opción sería un vector y un array 2d
    private static Map<String, Map<String, String>> tablas = new HashMap<>();

    public static final String LOGTAG = "DBManager";

    private DBManager(Context ctx){
        super( ctx, DB_NOMBRE, null, DB_VERSION );
        Log.d(LOGTAG, "Creando DBManager");
        Log.d(LOGTAG, "Nombre de la BD: " + super.getDatabaseName());
    }

    private void createMap(){
        Log.d(LOGTAG, "Creando mapa");
        tablas.clear();
        Arrays.sort(nombreTablas);

        Map<String, String> atributos = new HashMap<>();

        for(int i = 0; i < nombreTablas.length; i++) {
            Log.d(LOGTAG, i + " " + nombreTablas[i]);
            switch (nombreTablas[i]) {
                case WEAPON_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(ID_COLUMN, "INT NOT NULL");
                    atributos.put(NOMBRE_COLUMN, "varchar(30) NOT NULL");
                    atributos.put(RAREZA_COLUMN, "INT NOT NULL");
                    atributos.put(DANHO_COLUMN, "INT NOT NULL");
                    atributos.put(DEFENSA_COLUMN, "INT NOT NULL");
                    atributos.put(ELEMENTO_COLUMN, "varchar(9) NOT NULL");
                    atributos.put(ELEMENTOCANTIDAD_COLUMN, "INT NOT NULL");
                    atributos.put(ELEMENTOOCULTO_COLUMN, "BOOLEAN NOT NULL");
                    atributos.put(AFINIDAD_COLUMN, "INT NOT NULL");
                    atributos.put(SELLOANCIANOS_COLUMN, "varchar(5) NOT NULL");
                    atributos.put(IMGENLACE_COLUMN, "varchar(128) NOT NULL");
                    atributos.put(ICONENLACE_COLUMN, "varchar(128) NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case BOW_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(IDarmabase_COLUMN, "INT NOT NULL");
                    atributos.put(REVESTIMIENTO_COLUMN, "varchar(13) NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case CHARGEBLADE_TABLE:
                    atributos = new HashMap<>();
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case DUALBLADES_TABLE:
                    atributos = meterAfilado();
                    atributos.put(ELEMENTOSECUNDARIO_COLUMN, "varchar(9)");
                    atributos.put(ELEMENTOSECUNDARIOCANTIDAD_COLUMN, "INT");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case GREATSWORD_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case GUNLANCE_TABLE:
                    atributos = meterAfilado();
                    atributos.put(PROYECTILTIPO_COLUMN, "varchar(6) NOT NULL");
                    atributos.put(PROYECTILLVL_COLUMN, "INT NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case HAMMER_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case HEAVYBOWGUN_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(IDarmabase_COLUMN, "INT NOT NULL");
                    atributos.put(DESVIO_COLUMN, "varchar(5) NOT NULL");
                    atributos.put(MUNESPECIAL_COLUMN, "varchar(14) NOT NULL");
                    //TODO: Municiones
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case HUNTINGHORN_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case HUNTINGHORNNOTES_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(IDHH_COLUMN, "INT NOT NULL");
                    atributos.put(NOTA_COLUMN, "varchar(8) NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case INSECTGLAIVE_TABLE:
                    atributos = new HashMap<>();
                    atributos = meterAfilado();
                    atributos.put(BONUSINSECTO_COLUMN, "varchar(10) NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case LANCE_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case LIGHTBOWGUN_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(IDarmabase_COLUMN, "INT NOT NULL");
                    atributos.put(DESVIO_COLUMN, "varchar(5) NOT NULL");
                    //TODO: Municiones
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case LONGSWORD_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case SWITCHAXE_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case SWORDANDSHIELD_TABLE:
                    tablas.put(nombreTablas[i], meterAfilado());
                    break;

                case KINECTINSECT_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(ID_COLUMN, "INT NOT NULL");
                    atributos.put("tipo", "varchar(8) NOT NULL");
                    atributos.put("efecto", "varchar(8) NOT NULL");
                    atributos.put("stat_elemento", "INT NOT NULL");
                    atributos.put("stat_poder", "INT NOT NULL");
                    atributos.put("stat_velocidad", "INT NOT NULL");
                    atributos.put("stat_curacion", "INT NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case MEJORAS_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(IDarmabase_COLUMN, "INT NOT NULL");
                    atributos.put(IDarmamejora_COLUMN, "INT NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;

                case HUECOS_TABLE:
                    atributos = new HashMap<>();
                    atributos.put(IDarmabase_COLUMN, "INT NOT NULL");
                    atributos.put(HUECO_COLUMN, "INT NOT NULL");
                    tablas.put(nombreTablas[i], atributos);
                    break;
            }
        }
        Log.d("DBManager", tablas.toString());
    }

    private Map<String, String> meterAfilado(){
        Map<String, String> retorno = new HashMap<>();
        retorno.put(IDarmabase_COLUMN, "INT NOT NULL");
        retorno.put(AFILADOROJO_COLUMN, "INT NOT NULL");
        retorno.put(AFILADONARANJA_COLUMN, "INT NOT NULL");
        retorno.put(AFILADOAMARILLO_COLUMN, "INT NOT NULL");
        retorno.put(AFILADOVERDE_COLUMN, "INT NOT NULL");
        retorno.put(AFILADOAZUL_COLUMN, "INT NOT NULL");
        retorno.put(AFILADOBLANCO_COLUMN, "INT NOT NULL");
        return retorno;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        this.drop(db);
        this.createMap();

        Log.i( LOGTAG, "Creando BBDD " + DB_NOMBRE + " v" + DB_VERSION);
        try {
            db.beginTransaction();
            //El sql a ejecutar
            String sql = "";
            for(String TABLA_NOMBRE : tablas.keySet()){
                Log.d( LOGTAG + ".onCreate", "Creando tabla " + TABLA_NOMBRE);
                sql = ("\n\nDROP TABLE IF EXISTS " + TABLA_NOMBRE + "(\n");
                sql = ("\n\nCREATE TABLE IF NOT EXISTS " + TABLA_NOMBRE + "(\n");
                for(String ATRIBUTO : tablas.get(TABLA_NOMBRE).keySet()){
                    sql = sql + ATRIBUTO + " " + tablas.get(TABLA_NOMBRE).get(ATRIBUTO) + ",\n";
                }
                sql = sql + "\n" + constraints(TABLA_NOMBRE) + "\n)";
                Log.d(LOGTAG, "SQL: " + sql);
                db.execSQL(sql);
            }
            db.setTransactionSuccessful();
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    private String constraints(String tabla){
        String retorno = "";
        switch(tabla){
            case WEAPON_TABLE: case KINECTINSECT_TABLE:
                retorno =   "PRIMARY KEY(`ID`)\n";
                break;

            case BOW_TABLE:
                retorno =   "PRIMARY KEY(`ID_arma_base`, `revestimiento`),\n" +
                        "CONSTRAINT `fk_arma_base_" + tabla + "`\n" +
                        "FOREIGN KEY (`ID_arma_base`)\n" +
                        "REFERENCES `WEAPON` (`ID`)\n" +
                        "ON DELETE CASCADE\n" +
                        "ON UPDATE CASCADE";
                break;

            case CHARGEBLADE_TABLE: case DUALBLADES_TABLE: case GREATSWORD_TABLE: case GUNLANCE_TABLE: case HAMMER_TABLE: case HEAVYBOWGUN_TABLE: case HUNTINGHORN_TABLE:
            case INSECTGLAIVE_TABLE: case LANCE_TABLE: case LIGHTBOWGUN_TABLE: case LONGSWORD_TABLE: case SWITCHAXE_TABLE: case SWORDANDSHIELD_TABLE:
                retorno =   "PRIMARY KEY(`ID_arma_base`),\n" +
                        "CONSTRAINT `fk_arma_base_" + tabla + "`\n" +
                        "FOREIGN KEY (`ID_arma_base`)\n" +
                        "REFERENCES `WEAPON` (`ID`)\n" +
                        "ON DELETE CASCADE\n" +
                        "ON UPDATE CASCADE";
                break;

            case HUNTINGHORNNOTES_TABLE:
                retorno =   "PRIMARY KEY(`ID_HH`, `nota`),\n" +
                        "CONSTRAINT `fk_arma_base_" + tabla + "`\n" +
                        "FOREIGN KEY (`ID_HH`)\n" +
                        "REFERENCES `HUNTINGHORN` (`ID_arma_base`)\n" +
                        "ON DELETE CASCADE\n" +
                        "ON UPDATE CASCADE";
                break;

            case MEJORAS_TABLE:
                retorno =   "PRIMARY KEY (`ID_arma_base` , `ID_arma_mejora`)";/*,\n" +
                            "CONSTRAINT `fk_arma_base_" + tabla + "`\n" +
                            "FOREIGN KEY (`ID_arma_base`)\n" +
                            "REFERENCES `WEAPON` (`ID`)\n" +
                            "ON DELETE CASCADE\n" +
                            "ON UPDATE CASCADE,\n" +
                            "    CONSTRAINT `fk_arma_mejora`\n" +
                            "FOREIGN KEY (`ID_arma_mejora`)\n" +
                            "REFERENCES `WEAPON` (`ID`)\n" +
                            "ON DELETE CASCADE\n" +
                            "ON UPDATE CASCADE";*/
                break;

            case HUECOS_TABLE:
                retorno =   "CONSTRAINT `fk_arma_base_" + tabla + "`\n" +
                        "FOREIGN KEY (`ID_arma_base`)\n" +
                        "REFERENCES `WEAPON` (`ID`)\n" +
                        "ON DELETE CASCADE\n" +
                        "ON UPDATE CASCADE";
                break;
        }
        return retorno;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {
        Log.d(LOGTAG, "Eliminando datos del mapa");
        tablas.clear();
        Log.i( "DBManager", "DB: " + DB_NOMBRE + ": v" + v1 + " -> v" + v2 );
        try {
            db.beginTransaction();
            for(String TABLA_NOMBRE : tablas.keySet()){
                db.execSQL( "DROP TABLE IF EXISTS " + TABLA_NOMBRE );
            }
            db.setTransactionSuccessful();
        } catch(SQLException exc) {
            Log.e( "DBManager.onUpgrade", exc.getMessage() );
        } finally {
            db.endTransaction();
        }
        this.onCreate( db );
    }

    public static void drop(SQLiteDatabase db){
        Log.d(LOGTAG, "Eliminando datos");
        try {
            db.beginTransaction();
            for(String TABLA_NOMBRE : tablas.keySet()){
                Log.d(LOGTAG, "Eliminando tabla " + TABLA_NOMBRE);
                db.delete(TABLA_NOMBRE, null, null);
            }
            db.setTransactionSuccessful();
        } catch(SQLException exc) {
            Log.e( "DBManager.onUpgrade", exc.getMessage() );
        } finally {
            if(db.inTransaction()){
                db.endTransaction();
            }
        }
    }

    public static DBManager getInstance(){
        Log.d("DBManager", "Accediendo a instancia");
        if((singleton == null && getContext() != null) || changeContext){
            singleton = new DBManager(getContext());
            changeContext = false;
        }
        return singleton;
    }

    public static void setContext(Context ctx){
        if(context == null && context != ctx){
            changeContext = true;
            context = ctx;
        }
    }

    public static Context getContext(){
        return context;
    }

    public Cursor rawQuery(String query){
        Log.i(LOGTAG, "Ejecutando raw query: " + query);
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    public Cursor getTodoFromWhereSelect(String tabla, String condiciones, String[] atributos){
        if(atributos == null){
            Log.i(LOGTAG, "Ejecutando SELECT * FROM " + tabla + " WHERE " + condiciones);
        }else{
            Log.i(LOGTAG, "Ejecutando SELECT " + Arrays.toString(atributos).substring(1, Arrays.toString(atributos).length()-1).replace(",", " ") + " FROM " + tabla + " WHERE " + condiciones);
        }
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + tabla + " WHERE " + condiciones, atributos);
    }

    public Cursor getTodoFromSelect(String tabla, String[] atributos){
        return this.getTodoFromWhereSelect(tabla, "1", atributos);
    }

    public Cursor getTodoFromWhere(String tabla, String condiciones){
        return this.getTodoFromWhereSelect(tabla, condiciones, null);
    }

    public Cursor getTodoFrom(String tabla){
        return this.getTodoFromWhere(tabla, "1");
    }

    private boolean insertAfilado(int idArma, Map<Integer, Integer> afilado, String tabla){
        boolean retorno = false;

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            Log.d(LOGTAG, "Insertando afilado");
            BBDD.beginTransaction();
            if(afilado != null && afilado.size() == 6){
                BBDD.execSQL("INSERT INTO " + tabla + " (" + IDarmabase_COLUMN + ", " + AFILADOROJO_COLUMN + ", " + AFILADONARANJA_COLUMN + ", " +
                        AFILADOAMARILLO_COLUMN + ", " + AFILADOVERDE_COLUMN + ", " + AFILADOAZUL_COLUMN + ", " + AFILADOBLANCO_COLUMN + ") " +
                        "VALUES ("  + idArma + ", " + afilado.get(Weapon.AFILADOROJO) + ", " + afilado.get(Weapon.AFILADONARANJA) + ", " + afilado.get(Weapon.AFILADOAMARILLO)
                        + ", " + afilado.get(Weapon.AFILADOVERDE) + ", " + afilado.get(Weapon.AFILADOAZUL) + ", " + afilado.get(Weapon.AFILADOBLANCO) + ")");
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        } finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "Afilado insertada con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar Afilado, ID: " + idArma + " en tabla " + tabla);
        }
        return retorno;
    }

    private boolean insertWeapon(Weapon weapon){
        boolean retorno = false;
        Log.i(LOGTAG, "Insertando arma");

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            BBDD.execSQL(   "INSERT INTO WEAPON (ID, nombre, rareza, danho, defensa, elemento, elemento_cantidad, elemento_oculto, afinidad, sello_ancianos," +
                    "                            img_enlace, icon_enlace)" +
                    " VALUES (" + weapon.getID() + ", " + DatabaseUtils.sqlEscapeString(weapon.getNombre()) + ", " + weapon.getRareza() + ", " +
                    weapon.getDanho() + ", " + weapon.getDefensa() + ", " + DatabaseUtils.sqlEscapeString(weapon.getElemento()) + ", " +
                    weapon.getElementoCantidad() + ", " + (weapon.isElementoOculto() ? 1 : 0) + ", " + weapon.getAfinidad() + ", " +
                    DatabaseUtils.sqlEscapeString(weapon.getSelloAncianos()) + ", " + DatabaseUtils.sqlEscapeString(weapon.getImagen()) + ", " +
                    DatabaseUtils.sqlEscapeString(weapon.getIcon()) + ")");
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage() + " " + weapon.getID());
        } finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }
        if(retorno){
            Log.i(LOGTAG, "Arma insertada con éxito");
        }else{
            Log.i(LOGTAG, "Fallo al insertar arma, ID: " + weapon.getID());
        }
        return retorno;
    }

    private boolean insertMejoras(Weapon weapon){
        boolean retorno = false;
        Log.i(LOGTAG, "Insertando mejora");

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(weapon.getMejoras() != null && !weapon.getMejoras().isEmpty()  && !weapon.getMejoras().isEmpty()){
                for(Integer mejora : weapon.getMejoras()){
                    BBDD.execSQL("INSERT INTO MEJORAS (ID_arma_base, ID_arma_mejora) VALUES (" + weapon.getID() + ", " + mejora + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        } finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }
        if(retorno){
            Log.i(LOGTAG, "Mejora insertada con éxito");
        }else{
            Log.i(LOGTAG, "Fallo al insertar mejora");
        }
        return retorno;
    }

    private boolean insertHuecos(Weapon weapon){
        boolean retorno = false;
        Log.d(LOGTAG, "Insertando huecos");

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(weapon.getHuecosJoyas() != null && !weapon.getHuecosJoyas().isEmpty()  && !weapon.getHuecosJoyas().isEmpty()){
                for(Integer hueco : weapon.getHuecosJoyas()){
                    Log.d(LOGTAG, "Insertando hueco de " + weapon.getID() + ": " + hueco);
                    BBDD.execSQL("INSERT INTO HUECOS (" + IDarmabase_COLUMN + ", " + HUECO_COLUMN + ") VALUES (" + weapon.getID() + ", " + hueco + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        } finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }
        if(retorno){
            Log.d(LOGTAG, "Huecos insertados con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar huecos");
        }
        return retorno;
    }

    private boolean insertBow(Weapon arco){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arco);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arco);
            huecosInsertados = insertHuecos(arco);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando arco");
                BBDD.beginTransaction();
                if(((Bow) arco).getRevestimientos() != null && !((Bow) arco).getRevestimientos().isEmpty()){
                    for(String revestimiento : ((Bow) arco).getRevestimientos()){
                        BBDD.execSQL("INSERT INTO BOW (" + IDarmabase_COLUMN + ", " + REVESTIMIENTO_COLUMN + ") VALUES (" + arco.getID() + ", " + DatabaseUtils.sqlEscapeString(revestimiento) + ")");
                    }
                }
                BBDD.setTransactionSuccessful();
                retorno = true;
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        } finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }
        if(retorno){
            Log.d(LOGTAG, "Arco insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar arco, ID: " + arco.getID());
        }
        return retorno;
    }

    private boolean insertSnS(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando SnS");
                if(insertAfilado(arma.getID(), ((SwordandShield) arma).getAfilado(), SWORDANDSHIELD_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "SnS insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar SnS, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertSA(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando SwitchAxe");
                if(insertAfilado(arma.getID(), ((SwitchAxe) arma).getAfilado(), SWITCHAXE_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "SwitchAxe insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar SwitchAxe, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertLS(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando LS");
                if(insertAfilado(arma.getID(), ((LongSword) arma).getAfilado(), LONGSWORD_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "LongSword insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar LongSword, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertL(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando Lance");
                if(insertAfilado(arma.getID(), ((Lance) arma).getAfilado(), LANCE_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "Lance insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar Lance, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertH(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando Hammer");
                if(insertAfilado(arma.getID(), ((Hammer) arma).getAfilado(), HAMMER_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "Hammer insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar Hammer, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertGS(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando GreatSword");
                if(insertAfilado(arma.getID(), ((GreatSword) arma).getAfilado(), GREATSWORD_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "GreatSword insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar GreatSword, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertCB(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        try{
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando ChargeBlade");
                if(insertAfilado(arma.getID(), ((ChargeBlade) arma).getAfilado(), CHARGEBLADE_TABLE)){
                    retorno = true;
                }
            }
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }

        if(retorno){
            Log.d(LOGTAG, "ChargeBlade insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar ChargeBlade, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertGL(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando GunLance");
                if(((GunLance) arma).getAfilado() != null && ((GunLance) arma).getAfilado().size() == 6 && ((GunLance) arma).getProyectil() != null && ((GunLance) arma).getProyectil() != ""){
                    Map<Integer, Integer> afilado = ((GunLance) arma).getAfilado();
                    BBDD.execSQL("INSERT INTO " + GUNLANCE_TABLE + " (" + IDarmabase_COLUMN + ", " + AFILADOROJO_COLUMN + ", " + AFILADONARANJA_COLUMN + ", " +
                            AFILADOAMARILLO_COLUMN + ", " + AFILADOVERDE_COLUMN + ", " + AFILADOAZUL_COLUMN + ", " + AFILADOBLANCO_COLUMN + ", " +
                            PROYECTILTIPO_COLUMN + ", " + PROYECTILLVL_COLUMN + ") " +
                            "VALUES ("  + arma.getID() + ", " + afilado.get(Weapon.AFILADOROJO) + ", " + afilado.get(Weapon.AFILADONARANJA) + ", " + afilado.get(Weapon.AFILADOAMARILLO)
                            + ", " + afilado.get(Weapon.AFILADOVERDE) + ", " + afilado.get(Weapon.AFILADOAZUL) + ", " + afilado.get(Weapon.AFILADOBLANCO) +
                            ", " + DatabaseUtils.sqlEscapeString(((GunLance) arma).getProyectil()) + ", " + ((GunLance) arma).getLvlProyectil() + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "GunLance insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar GunLance, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertDB(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando DualBlades");
                if(((DualBlades) arma).getAfilado() != null && ((DualBlades) arma).getAfilado().size() == 6){
                    Map<Integer, Integer> afilado = ((DualBlades) arma).getAfilado();
                    BBDD.execSQL("INSERT INTO " + DUALBLADES_TABLE + " (" + IDarmabase_COLUMN + ", " + AFILADOROJO_COLUMN + ", " + AFILADONARANJA_COLUMN + ", " +
                            AFILADOAMARILLO_COLUMN + ", " + AFILADOVERDE_COLUMN + ", " + AFILADOAZUL_COLUMN + ", " + AFILADOBLANCO_COLUMN + ", " +
                            ELEMENTOSECUNDARIO_COLUMN + ", " + ELEMENTOSECUNDARIOCANTIDAD_COLUMN + ") " +
                            "VALUES ("  + arma.getID() + ", " + afilado.get(Weapon.AFILADOROJO) + ", " + afilado.get(Weapon.AFILADONARANJA) + ", " + afilado.get(Weapon.AFILADOAMARILLO)
                            + ", " + afilado.get(Weapon.AFILADOVERDE) + ", " + afilado.get(Weapon.AFILADOAZUL) + ", " +  afilado.get(Weapon.AFILADOBLANCO) + ", "
                            + DatabaseUtils.sqlEscapeString(((DualBlades) arma).getElem2()) + ", " + ((DualBlades) arma).getElem2cantidad() + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "DualBlades insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar DualBlades, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertIG(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando InsectGlaive");
                if(((InsectGlaive) arma).getAfilado() != null && ((InsectGlaive) arma).getAfilado().size() == 6 && ((InsectGlaive) arma).getBonusInsecto() != null && ((InsectGlaive) arma).getBonusInsecto() != ""){
                    Map<Integer, Integer> afilado = ((InsectGlaive) arma).getAfilado();
                    BBDD.execSQL("INSERT INTO " + INSECTGLAIVE_TABLE + " (" + IDarmabase_COLUMN + ", " + AFILADOROJO_COLUMN + ", " + AFILADONARANJA_COLUMN + ", " +
                            AFILADOAMARILLO_COLUMN + ", " + AFILADOVERDE_COLUMN + ", " + AFILADOAZUL_COLUMN + ", " + AFILADOBLANCO_COLUMN + ", " +
                            BONUSINSECTO_COLUMN + ") " +
                            "VALUES ("  + arma.getID() + ", " + afilado.get(Weapon.AFILADOROJO) + ", " + afilado.get(Weapon.AFILADONARANJA) + ", " + afilado.get(Weapon.AFILADOAMARILLO)
                            + ", " + afilado.get(Weapon.AFILADOVERDE) + ", " + afilado.get(Weapon.AFILADOAZUL) + ", " + afilado.get(Weapon.AFILADOBLANCO)
                            + ", " + DatabaseUtils.sqlEscapeString(((InsectGlaive) arma).getBonusInsecto()) + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "InsectGlaive insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar InsectGlaive, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertHH(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando HuntingHorn");
                if(((HuntingHorn) arma).getAfilado() != null && ((HuntingHorn) arma).getAfilado().size() == 6) {
                    Map<Integer, Integer> afilado = ((HuntingHorn) arma).getAfilado();
                    BBDD.execSQL("INSERT INTO " + HUNTINGHORN_TABLE + " (" + IDarmabase_COLUMN + ", " + AFILADOROJO_COLUMN + ", " + AFILADONARANJA_COLUMN + ", " +
                            AFILADOAMARILLO_COLUMN + ", " + AFILADOVERDE_COLUMN + ", " + AFILADOAZUL_COLUMN + ", " + AFILADOBLANCO_COLUMN + ") " +
                            "VALUES ("  + arma.getID() + ", " + afilado.get(Weapon.AFILADOROJO) + ", " + afilado.get(Weapon.AFILADONARANJA) + ", " + afilado.get(Weapon.AFILADOAMARILLO)
                            + ", " + afilado.get(Weapon.AFILADOVERDE) + ", " + afilado.get(Weapon.AFILADOAZUL) + ", " + afilado.get(Weapon.AFILADOBLANCO) + ")");
                }
                Log.d(LOGTAG, "Notas de " + arma.getID() + ": " + ((HuntingHorn) arma).getNotas().toString());

                if(((HuntingHorn) arma).getNotas() != null && !((HuntingHorn) arma).getNotas().isEmpty()){
                    Log.d(LOGTAG, "Insertando Notas de " + arma.getID());
                    for(String nota : ((HuntingHorn) arma).getNotas()){
                        BBDD.execSQL("INSERT INTO " + HUNTINGHORNNOTES_TABLE + " (" + IDHH_COLUMN + ", " + NOTA_COLUMN + ") VALUES (" + arma.getID() + ", " + DatabaseUtils.sqlEscapeString(nota) + ")");
                    }
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "HuntingHorn insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar HuntingHorn, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertLB(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando LightBowgun");
                if(((LightBowgun) arma).getDesvio() != null && !((LightBowgun) arma).getDesvio().equals("")) {
                    BBDD.execSQL("INSERT INTO " + LIGHTBOWGUN_TABLE + " (" + IDarmabase_COLUMN + ", " + DESVIO_COLUMN + ") " +
                            "VALUES (" + arma.getID() + ", " + DatabaseUtils.sqlEscapeString(((LightBowgun) arma).getDesvio()) + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "LightBowgun insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar LightBowgun, ID: " + arma.getID());
        }
        return retorno;
    }

    private boolean insertHB(Weapon arma){
        boolean retorno = false;
        boolean mejorasInsertadas = false;
        boolean huecosInsertados = false;
        boolean armaInsertada = insertWeapon(arma);
        if(armaInsertada){
            mejorasInsertadas = insertMejoras(arma);
            huecosInsertados = insertHuecos(arma);
        }

        SQLiteDatabase BBDD = getInstance().getWritableDatabase();
        try{
            BBDD.beginTransaction();
            if(armaInsertada && mejorasInsertadas && huecosInsertados){
                Log.d(LOGTAG, "Insertando HeavyBowgun");
                if(((HeavyBowgun) arma).getDesvio() != null && !((HeavyBowgun) arma).getDesvio().equals("")) {
                    BBDD.execSQL("INSERT INTO " + HEAVYBOWGUN_TABLE + " (" + IDarmabase_COLUMN + ", " + DESVIO_COLUMN + ", " + MUNESPECIAL_COLUMN + ") " +
                            "VALUES (" + arma.getID() + ", " + DatabaseUtils.sqlEscapeString(((HeavyBowgun) arma).getDesvio()) + ", " +
                            DatabaseUtils.sqlEscapeString(((HeavyBowgun) arma).getMunicionEspecial()) + ")");
                }
            }
            BBDD.setTransactionSuccessful();
            retorno = true;
        } catch(SQLException exc) {
            Log.e(LOGTAG, exc.getMessage());
        }finally {
            if(BBDD.inTransaction()){
                BBDD.endTransaction();
            }
        }

        if(retorno){
            Log.d(LOGTAG, "HeavyBowgun insertado con éxito");
        }else{
            Log.d(LOGTAG, "Fallo al insertar HeavyBowgun, ID: " + arma.getID());
        }
        return retorno;
    }

    /**1connectpoint para insertar arma**/
    public boolean guardarArma(Weapon weapon){
        if(!exists(weapon.getID())){
            switch(weapon.getClass().getSimpleName()){
                case "Bow":
                    return this.insertBow(weapon);
                case "ChargeBlade":
                    return this.insertCB(weapon);
                case "DualBlades":
                    return this.insertDB(weapon);
                case "GreatSword":
                    return this.insertGS(weapon);
                case "GunLance":
                    return this.insertGL(weapon);
                case "Hammer":
                    return this.insertH(weapon);
                case "HeavyBowgun":
                    return this.insertHB(weapon);
                case "HuntingHorn":
                    return this.insertHH(weapon);
                case "InsectGlaive":
                    return this.insertIG(weapon);
                case "Lance":
                    return this.insertL(weapon);
                case "LightBowgun":
                    return this.insertLB(weapon);
                case "LongSword":
                    return this.insertLS(weapon);
                case "SwitchAxe":
                    return this.insertSA(weapon);
                case "SwordandShield":
                    Log.d(LOGTAG, "Insertando SnS");
                    return this.insertSnS(weapon);
                default:
                    return false;
            }
        }else{
            return false;
        }
    }

    public Cursor getWeapons(String tipoArma){
        Log.d(LOGTAG, "Tipo de arma consultado: " + tipoArma + ", " + SWORDANDSHIELD_TABLE);
        Cursor retorno = null;
        SQLiteDatabase db = this.getReadableDatabase();

        if(Arrays.binarySearch(nombreTablas, tipoArma.toUpperCase()) != -1 && !tipoArma.equals(KINECTINSECT_TABLE)){
            retorno = db.rawQuery("SELECT * FROM WEAPON, " + tipoArma + " WHERE WEAPON." + ID_COLUMN + " = " + tipoArma + ".ID_arma_base" , null);
        }

        return retorno;
    }

    public Weapon getWeapon(String tipoArma, int id){
        Weapon retorno = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(LOGTAG, "Cogiendo arma: " + id + ", de tipo " + tipoArma);

        try{
            Cursor weapon = db.rawQuery("SELECT * FROM WEAPON WHERE WEAPON." + ID_COLUMN + " = " + id, null);
            Cursor mejoras = db.rawQuery("SELECT * FROM MEJORAS WHERE MEJORAS." + IDarmabase_COLUMN + " = " + id, null);
            Cursor huecos = db.rawQuery("SELECT * FROM HUECOS WHERE HUECOS." + IDarmabase_COLUMN + " = " + id, null);

            if(weapon != null){
                switch(tipoArma.toUpperCase()) {
                    case BOW_TABLE:
                        retorno = new Bow();
                        Cursor revestimientos = db.rawQuery("SELECT * FROM BOW WHERE BOW." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "Coating attributes: " + Arrays.toString(revestimientos.getColumnNames()));
                        List<String> revestimientosList = new LinkedList<>();

                        while (revestimientos.moveToNext()) {
                            for (int i = 0; i < revestimientos.getColumnCount(); i++) {
                                switch (revestimientos.getColumnName(i)) {
                                    case REVESTIMIENTO_COLUMN:
                                        Log.d(LOGTAG, REVESTIMIENTO_COLUMN + ": " + revestimientos.getString(i));
                                        revestimientosList.add(revestimientos.getString(i));
                                        break;
                                }
                            }
                        }

                        ((Bow) retorno).setRevestimientos(revestimientosList);
                        break;

                    case CHARGEBLADE_TABLE: case GREATSWORD_TABLE: case HAMMER_TABLE: case LANCE_TABLE: case LONGSWORD_TABLE: case SWITCHAXE_TABLE: case SWORDANDSHIELD_TABLE:
                        Cursor afilado = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "Afilado attributes: " + Arrays.toString(afilado.getColumnNames()));
                        Map<Integer, Integer> afiladoMap = new HashMap<>();

                        while (afilado.moveToNext()) {
                            for (int i = 0; i < afilado.getColumnCount(); i++) {
                                switch (afilado.getColumnName(i)) {
                                    case AFILADOROJO_COLUMN:
                                        afiladoMap.put(Weapon.AFILADOROJO, afilado.getInt(i));
                                        break;
                                    case AFILADONARANJA_COLUMN:
                                        afiladoMap.put(Weapon.AFILADONARANJA, afilado.getInt(i));
                                        break;
                                    case AFILADOAMARILLO_COLUMN:
                                        afiladoMap.put(Weapon.AFILADOAMARILLO, afilado.getInt(i));
                                        break;
                                    case AFILADOVERDE_COLUMN:
                                        afiladoMap.put(Weapon.AFILADOVERDE, afilado.getInt(i));
                                        break;
                                    case AFILADOAZUL_COLUMN:
                                        afiladoMap.put(Weapon.AFILADOAZUL, afilado.getInt(i));
                                        break;
                                    case AFILADOBLANCO_COLUMN:
                                        afiladoMap.put(Weapon.AFILADOBLANCO, afilado.getInt(i));
                                        break;
                                }
                            }
                        }

                        if(tipoArma.equals(CHARGEBLADE_TABLE)){
                            retorno = new ChargeBlade();
                            ((ChargeBlade) retorno).setAfilado(afiladoMap);
                        }else if(tipoArma.equals(GREATSWORD_TABLE)){
                            retorno = new GreatSword();
                            ((GreatSword) retorno).setAfilado(afiladoMap);
                        }else if(tipoArma.equals(HAMMER_TABLE)){
                            retorno = new Hammer();
                            ((Hammer) retorno).setAfilado(afiladoMap);
                        }else if(tipoArma.equals(LANCE_TABLE)){
                            retorno = new Lance();
                            ((Lance) retorno).setAfilado(afiladoMap);
                        }else if(tipoArma.equals(LONGSWORD_TABLE)){
                            retorno = new LongSword();
                            ((LongSword) retorno).setAfilado(afiladoMap);
                        }else if(tipoArma.equals(SWITCHAXE_TABLE)){
                            retorno = new SwitchAxe();
                            ((SwitchAxe) retorno).setAfilado(afiladoMap);
                        }else{ //Swordandshield
                            retorno = new SwordandShield();
                            ((SwordandShield) retorno).setAfilado(afiladoMap);
                        }

                        break;

                    case DUALBLADES_TABLE:
                        retorno = new DualBlades();
                        Cursor dualBlade = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "DualBlades attributes: " + Arrays.toString(dualBlade.getColumnNames()));
                        Map<Integer, Integer> afiladoMapDB = new HashMap<>();

                        while (dualBlade.moveToNext()) {
                            for (int i = 0; i < dualBlade.getColumnCount(); i++) {
                                switch (dualBlade.getColumnName(i)) {
                                    case AFILADOROJO_COLUMN:
                                        afiladoMapDB.put(Weapon.AFILADOROJO, dualBlade.getInt(i));
                                        break;
                                    case AFILADONARANJA_COLUMN:
                                        afiladoMapDB.put(Weapon.AFILADONARANJA, dualBlade.getInt(i));
                                        break;
                                    case AFILADOAMARILLO_COLUMN:
                                        afiladoMapDB.put(Weapon.AFILADOAMARILLO, dualBlade.getInt(i));
                                        break;
                                    case AFILADOVERDE_COLUMN:
                                        afiladoMapDB.put(Weapon.AFILADOVERDE, dualBlade.getInt(i));
                                        break;
                                    case AFILADOAZUL_COLUMN:
                                        afiladoMapDB.put(Weapon.AFILADOAZUL, dualBlade.getInt(i));
                                        break;
                                    case AFILADOBLANCO_COLUMN:
                                        afiladoMapDB.put(Weapon.AFILADOBLANCO, dualBlade.getInt(i));
                                        break;
                                    case ELEMENTOSECUNDARIO_COLUMN:
                                        ((DualBlades) retorno).setElem2(dualBlade.getString(i));
                                        break;
                                    case ELEMENTOSECUNDARIOCANTIDAD_COLUMN:
                                        ((DualBlades) retorno).setElem2cantidad(dualBlade.getInt(i));
                                        break;
                                }
                            }
                        }

                        ((DualBlades) retorno).setAfilado(afiladoMapDB);

                        break;

                    case GUNLANCE_TABLE:
                        retorno = new GunLance();
                        Cursor GunLance = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "GunLance attributes: " + Arrays.toString(GunLance.getColumnNames()));
                        Map<Integer, Integer> afiladoMapGL = new HashMap<>();

                        while (GunLance.moveToNext()) {
                            for (int i = 0; i < GunLance.getColumnCount(); i++) {
                                switch (GunLance.getColumnName(i)) {
                                    case AFILADOROJO_COLUMN:
                                        afiladoMapGL.put(Weapon.AFILADOROJO, GunLance.getInt(i));
                                        break;
                                    case AFILADONARANJA_COLUMN:
                                        afiladoMapGL.put(Weapon.AFILADONARANJA, GunLance.getInt(i));
                                        break;
                                    case AFILADOAMARILLO_COLUMN:
                                        afiladoMapGL.put(Weapon.AFILADOAMARILLO, GunLance.getInt(i));
                                        break;
                                    case AFILADOVERDE_COLUMN:
                                        afiladoMapGL.put(Weapon.AFILADOVERDE, GunLance.getInt(i));
                                        break;
                                    case AFILADOAZUL_COLUMN:
                                        afiladoMapGL.put(Weapon.AFILADOAZUL, GunLance.getInt(i));
                                        break;
                                    case AFILADOBLANCO_COLUMN:
                                        afiladoMapGL.put(Weapon.AFILADOBLANCO, GunLance.getInt(i));
                                        break;
                                    case PROYECTILTIPO_COLUMN:
                                        ((GunLance) retorno).setProyectil(GunLance.getString(i));
                                        break;
                                    case PROYECTILLVL_COLUMN:
                                        ((GunLance) retorno).setLvlProyectil(GunLance.getInt(i));
                                        break;
                                }
                            }
                        }
                        ((GunLance) retorno).setAfilado(afiladoMapGL);
                        break;

                    case HEAVYBOWGUN_TABLE:
                        retorno = new HeavyBowgun();
                        Cursor HeavyBowgun = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "HeavyBowgun attributes: " + Arrays.toString(HeavyBowgun.getColumnNames()));

                        while (HeavyBowgun.moveToNext()) {
                            for (int i = 0; i < HeavyBowgun.getColumnCount(); i++) {
                                switch (HeavyBowgun.getColumnName(i)) {
                                    case DESVIO_COLUMN:
                                        ((HeavyBowgun) retorno).setDesvio(HeavyBowgun.getString(i));
                                        break;
                                    case MUNESPECIAL_COLUMN:
                                        ((HeavyBowgun) retorno).setMunicionEspecial(HeavyBowgun.getString(i));
                                        break;
                                }
                            }
                        }

                        break;

                    case HUNTINGHORN_TABLE:
                        retorno = new HuntingHorn();
                        Cursor HuntingHorn = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Cursor Notas = db.rawQuery("SELECT * FROM " + HUNTINGHORNNOTES_TABLE + " WHERE " + HUNTINGHORNNOTES_TABLE + "." + IDHH_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "HuntingHorn attributes: " + Arrays.toString(HuntingHorn.getColumnNames()));
                        Map<Integer, Integer> afiladoMapHH = new HashMap<>();

                        while (HuntingHorn.moveToNext()) {
                            for (int i = 0; i < HuntingHorn.getColumnCount(); i++) {
                                switch (HuntingHorn.getColumnName(i)) {
                                    case AFILADOROJO_COLUMN:
                                        afiladoMapHH.put(Weapon.AFILADOROJO, HuntingHorn.getInt(i));
                                        break;
                                    case AFILADONARANJA_COLUMN:
                                        afiladoMapHH.put(Weapon.AFILADONARANJA, HuntingHorn.getInt(i));
                                        break;
                                    case AFILADOAMARILLO_COLUMN:
                                        afiladoMapHH.put(Weapon.AFILADOAMARILLO, HuntingHorn.getInt(i));
                                        break;
                                    case AFILADOVERDE_COLUMN:
                                        afiladoMapHH.put(Weapon.AFILADOVERDE, HuntingHorn.getInt(i));
                                        break;
                                    case AFILADOAZUL_COLUMN:
                                        afiladoMapHH.put(Weapon.AFILADOAZUL, HuntingHorn.getInt(i));
                                        break;
                                    case AFILADOBLANCO_COLUMN:
                                        afiladoMapHH.put(Weapon.AFILADOBLANCO, HuntingHorn.getInt(i));
                                        break;
                                }
                            }
                        }

                        List<String> NotasHH = new LinkedList<>();
                        if(Notas != null){
                            while (Notas.moveToNext()) {
                                for (int i = 0; i < Notas.getColumnCount(); i++) {
                                    switch (HuntingHorn.getColumnName(i)) {
                                        case NOTA_COLUMN:
                                            NotasHH.add(Notas.getString(i));
                                            break;
                                    }
                                }
                            }
                        }

                        ((HuntingHorn) retorno).setAfilado(afiladoMapHH);
                        ((HuntingHorn) retorno).setNotas(NotasHH);
                        break;

                    case INSECTGLAIVE_TABLE:
                        retorno = new InsectGlaive();
                        Cursor InsectGlaive = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "InsectGlaive attributes: " + Arrays.toString(InsectGlaive.getColumnNames()));
                        Map<Integer, Integer> afiladoMapIG = new HashMap<>();

                        while (InsectGlaive.moveToNext()) {
                            for (int i = 0; i < InsectGlaive.getColumnCount(); i++) {
                                switch (InsectGlaive.getColumnName(i)) {
                                    case AFILADOROJO_COLUMN:
                                        afiladoMapIG.put(Weapon.AFILADOROJO, InsectGlaive.getInt(i));
                                        break;
                                    case AFILADONARANJA_COLUMN:
                                        afiladoMapIG.put(Weapon.AFILADONARANJA, InsectGlaive.getInt(i));
                                        break;
                                    case AFILADOAMARILLO_COLUMN:
                                        afiladoMapIG.put(Weapon.AFILADOAMARILLO, InsectGlaive.getInt(i));
                                        break;
                                    case AFILADOVERDE_COLUMN:
                                        afiladoMapIG.put(Weapon.AFILADOVERDE, InsectGlaive.getInt(i));
                                        break;
                                    case AFILADOAZUL_COLUMN:
                                        afiladoMapIG.put(Weapon.AFILADOAZUL, InsectGlaive.getInt(i));
                                        break;
                                    case AFILADOBLANCO_COLUMN:
                                        afiladoMapIG.put(Weapon.AFILADOBLANCO, InsectGlaive.getInt(i));
                                        break;
                                    case BONUSINSECTO_COLUMN:
                                        ((InsectGlaive) retorno).setBonusInsecto(InsectGlaive.getString(i));
                                        break;
                                }
                            }
                        }

                        ((InsectGlaive) retorno).setAfilado(afiladoMapIG);
                        break;

                    case LIGHTBOWGUN_TABLE:
                        retorno = new LightBowgun();
                        Cursor LightBowgun = db.rawQuery("SELECT * FROM " + tipoArma + " WHERE " + tipoArma + "." + IDarmabase_COLUMN + " = " + id, null);
                        Log.d(LOGTAG, "LightBowgun attributes: " + Arrays.toString(LightBowgun.getColumnNames()));

                        while (LightBowgun.moveToNext()) {
                            for (int i = 0; i < LightBowgun.getColumnCount(); i++) {
                                switch (LightBowgun.getColumnName(i)) {
                                    case DESVIO_COLUMN:
                                        ((LightBowgun) retorno).setDesvio(LightBowgun.getString(i));
                                        break;
                                }
                            }
                        }

                        break;

                    default:
                        return null;
                }

                /**Coger atributos del arma, despues de hacer que sea de un tipo específico**/
                while (weapon.moveToNext()) {
                    for (int i = 0; i < weapon.getColumnCount(); i++) {
                        switch (weapon.getColumnName(i)) {
                            case ID_COLUMN:
                                Log.d(LOGTAG, ID_COLUMN + ": " + weapon.getInt(i));
                                retorno.setID(weapon.getInt(i));
                                break;
                            case RAREZA_COLUMN:
                                Log.d(LOGTAG, RAREZA_COLUMN + ": " + weapon.getInt(i));
                                retorno.setRareza(weapon.getInt(i));
                                break;
                            case NOMBRE_COLUMN:
                                Log.d(LOGTAG, NOMBRE_COLUMN + ": " + weapon.getString(i));
                                retorno.setNombre(weapon.getString(i));
                                break;
                            case DANHO_COLUMN:
                                Log.d(LOGTAG, DANHO_COLUMN + ": " + weapon.getInt(i));
                                retorno.setDanho(weapon.getInt(i));
                                break;
                            case AFINIDAD_COLUMN:
                                Log.d(LOGTAG, AFINIDAD_COLUMN + ": " + weapon.getInt(i));
                                retorno.setAfinidad(weapon.getInt(i));
                                break;
                            case DEFENSA_COLUMN:
                                Log.d(LOGTAG, DEFENSA_COLUMN + ": " + weapon.getInt(i));
                                retorno.setDefensa(weapon.getInt(i));
                                break;
                            case SELLOANCIANOS_COLUMN:
                                Log.d(LOGTAG, SELLOANCIANOS_COLUMN + ": " + weapon.getString(i));
                                retorno.setSelloAncianos(weapon.getString(i));
                                break;
                            case ELEMENTO_COLUMN:
                                Log.d(LOGTAG, ELEMENTO_COLUMN + ": " + weapon.getString(i));
                                retorno.setElemento(weapon.getString(i));
                                break;
                            case ELEMENTOCANTIDAD_COLUMN:
                                Log.d(LOGTAG, ELEMENTOCANTIDAD_COLUMN + ": " + weapon.getInt(i));
                                retorno.setElementoCantidad(weapon.getInt(i));
                                break;
                            case ELEMENTOOCULTO_COLUMN:
                                Log.d(LOGTAG, ELEMENTOOCULTO_COLUMN + ": " + weapon.getInt(i));
                                retorno.setElementoOculto(weapon.getInt(i) == 1 ? true : false);
                                break;
                            case ICONENLACE_COLUMN:
                                Log.d(LOGTAG, ICONENLACE_COLUMN + ": " + weapon.getString(i));
                                retorno.setIcon(weapon.getString(i));
                                break;
                            case IMGENLACE_COLUMN:
                                Log.d(LOGTAG, IMGENLACE_COLUMN + ": " + weapon.getString(i));
                                retorno.setImagen(weapon.getString(i));
                                break;
                            default:
                                break;
                        }
                    }
                }

                /**Ahora comprobar huecos y mejoras**/
                if(huecos != null){
                    List<Integer> huecosList = new LinkedList<>();

                    while (huecos.moveToNext()) {
                        for (int i = 0; i < huecos.getColumnCount(); i++) {
                            switch (huecos.getColumnName(i)) {
                                case HUECO_COLUMN:
                                    Log.d(LOGTAG, HUECO_COLUMN + ": " + huecos.getInt(i));
                                    huecosList.add(huecos.getInt(i));
                                    break;
                            }
                        }
                    }

                    retorno.setHuecosJoyas(huecosList);
                }

                if(mejoras != null){
                    List<Integer> mejorasList = new LinkedList<>();

                    while (mejoras.moveToNext()) {
                        for (int i = 0; i < mejoras.getColumnCount(); i++) {
                            switch (mejoras.getColumnName(i)) {
                                case IDarmamejora_COLUMN:
                                    Log.d(LOGTAG, IDarmamejora_COLUMN + ": " + mejoras.getInt(i));
                                    mejorasList.add(mejoras.getInt(i));
                                    break;
                            }
                        }
                    }

                    retorno.setMejoras(mejorasList);
                }
            }
        }catch(Exception e){
            Log.e(LOGTAG, "Consultando arma: " + e.getMessage());
        }

        return retorno;
    }

    private boolean exists(int id){
        boolean retorno = false;

        SQLiteDatabase BBDD = getReadableDatabase();

        try{
            Cursor cursor = BBDD.rawQuery("SELECT * FROM " + WEAPON_TABLE + " WHERE " + WEAPON_TABLE + "." + ID_COLUMN + " = " + id, null);
            if(cursor.moveToNext() && cursor != null){
                Log.i(LOGTAG, "El arma ya existe en la BBDD");
                retorno = true;
            }
        }catch(Exception e){
            Log.e(LOGTAG, e.toString());
        }

        return retorno;
    }

}

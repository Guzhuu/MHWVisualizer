package com.es.google.mhwvisualizer.Core.Gear;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Weapon extends Gear {
    //TODO: Internazionalizacion aka poenr todo en strings
    public static final String[] desvios = {"Bajo", "Medio", "Alto"};
    public static final String[] revestimientosPosibles = new String[]{"Corto Alcance", "Poder", "Veneno", "Parálisis", "Sueño", "Nitro"};
    public static final String[] municionesEspeciales = new String[]{"Corazón de wyvern", "Francotirador wyvern"};
    public static final String[] bonusInsectosPosibles = new String[]{"Corte", "Elemental", "Salud", "Resistencia", "Impacto", "Velocidad"};
    public static final String[] proyectilesGunlance = new String[]{"Normal", "Largo", "Abanico"};
    public static final int[] lvlProyectiles = new int[]{1, 2, 3, 4};
    public static final String[] notasPosibles = new String[]{"Morada", "Azul", "Añil", "Verde", "Amarilla", "Naranja", "Roja"};
    public static final String[] sellosPosibles = new String[]{"", "Bajo", "Medio", "Alto"};

    public static final Integer AFILADOROJO = 0;
    public static final Integer AFILADONARANJA = 1;
    public static final Integer AFILADOAMARILLO = 2;
    public static final Integer AFILADOVERDE = 3;
    public static final Integer AFILADOAZUL = 4;
    public static final Integer AFILADOBLANCO = 5;

    public static final String FUEGO = "fire";
    public static final String AGUA = "water";
    public static final String TRUENO = "thunder";
    public static final String HIELO = "ice";
    public static final String DRAGON = "dragon";
    public static final String VENENO = "poison";
    public static final String PARALISIS = "paralysis";
    public static final String NITRO = "blast";
    public static final String SUENHO = "sleep";
    public static final String CORTOALCANCE = "close range";
    public static final String PODER = "power";

    public static final HashMap<String, String> selloAncianosMap = new HashMap<>();

    public int rareza;
    public int danho;
    public int defensa;
    public String elemento;
    public int elementoCantidad;
    public boolean elementoOculto;
    public int afinidad;
    public String selloAncianos;

    //Armas a las que esta se puede mejorar (IDS)
    public List<Integer> mejoras;

    public Weapon(){
        super();
    }

    public Weapon(String nombre, int rareza, int dmg, int defensa, String elem, int elemC, boolean elemO, int afinidad, List<Integer> huecos, String selloAncianos,
                  List<Integer> mejoras){
        super(nombre);
        this.rareza = rareza;
        this.danho = dmg;
        this.defensa = defensa;
        this.elemento = elem;
        this.elementoCantidad = elemC;
        this.elementoOculto = elemO;
        this.afinidad = afinidad;
        this.selloAncianos = selloAncianos;
        this.huecosJoyas = huecos;
        this.mejoras = mejoras;
    }

    public Weapon(String nombre, int rareza, int dmg, int defensa, String elem, int elemC, boolean elemO, int afinidad, List<Integer> huecos, String selloAncianos,
                  List<Integer> mejoras, String img, String icon){
        super(nombre);
        this.rareza = rareza;
        this.danho = dmg;
        this.defensa = defensa;
        this.elemento = elem;
        this.elementoCantidad = elemC;
        this.elementoOculto = elemO;
        this.afinidad = afinidad;
        this.selloAncianos = selloAncianos;
        this.huecosJoyas = huecos;
        this.mejoras = mejoras;
        this.setImagen(img);
        this.setImagen(icon);
    }

    public int getDanho() {
        return danho;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getElemento() {
        if(elemento == null){
            return "no";
        }
        return elemento;
    }

    public int getElementoCantidad() {
        return elementoCantidad;
    }

    public boolean isElementoOculto() {
        return elementoOculto;
    }

    public int getAfinidad() {
        return afinidad;
    }

    public String getSelloAncianos() {
        if(selloAncianos == null || selloAncianos.equals("") || selloAncianos.isEmpty()){
            return "Sin bonus";
        }
        if(selloAncianosMap.isEmpty()){
            selloAncianosMap.put("Sin bonus", "Sin bonus");
            selloAncianosMap.put("low", "Bajo");
            selloAncianosMap.put("average", "Medio");
            selloAncianosMap.put("high", "Alto");
        }
        if(selloAncianosMap.containsKey(this.selloAncianos)){
            return selloAncianosMap.get(this.selloAncianos);
        }else if(selloAncianosMap.values().contains(this.selloAncianos)) {
            return this.selloAncianos;
        }else{
            Log.d("WEAPON", "No se ha encontrado el sello ancianos: " + this.selloAncianos);
            return "No encontrado";
        }
    }

    public void setDanho(int danho) {
        this.danho = danho;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public void setElementoCantidad(int elementoCantidad) {
        this.elementoCantidad = elementoCantidad;
    }

    public void setElementoOculto(boolean elementoOculto) {
        this.elementoOculto = elementoOculto;
    }

    public void setAfinidad(int afinidad) {
        this.afinidad = afinidad;
    }

    public void setSelloAncianos(String selloAncianos) {
        this.selloAncianos = selloAncianos;
    }

    public int getRareza() {
        return rareza;
    }

    public void setRareza(int rareza) {
        this.rareza = rareza;
    }

    public List<Integer> getMejoras() {
        if(mejoras == null){
            return new LinkedList<Integer>();
        }
        return mejoras;
    }

    public void setMejoras(List<Integer> mejoras) {
        this.mejoras = mejoras;
    }

}

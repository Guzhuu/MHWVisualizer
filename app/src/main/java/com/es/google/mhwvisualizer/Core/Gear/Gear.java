package com.es.google.mhwvisualizer.Core.Gear;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Gear {
    private static String LOGTAG = "GEAR";
    //Diferentes elementos que pueden tener las armas
    public static final String[] elementosPosibles = new String[]{"", "Fuego", "Agua", "Trueno", "Hielo", "Dragon", "Veneno", "Sueño", "Paralisis", "Nitro"};
    public static final int[] huecosPosibles = new int[]{1, 2, 3};
    public static final int[] rarezasPosibles = new int[]{1,2,3,4,5,6,7,8};

    private String nombre;
    private boolean enPropiedad;
    private int ID;

    //Huecos y de que tipo. Máximo 3
    public List<Integer> huecosJoyas;

    //URLS de las imagenes
    private String imagen;
    private String icon;
    private String tipo;

    public Gear(){

    }

    public Gear(String nombre){
        this.nombre = nombre;
    }

    public void setEnPropiedad(Boolean b){
        this.enPropiedad = b;
    }

    public Boolean getEnPropiedad(){
        return this.enPropiedad;
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int id){
        this.ID = id;
    }

    public String getNombre() {
        //TODO: Traducciones?
        if(nombre == null){
            return "unnamed";
        }
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnPropiedad() {
        return enPropiedad;
    }

    public void setEnPropiedad(boolean enPropiedad) {
        this.enPropiedad = enPropiedad;
    }

    public List<Integer> getHuecosJoyas() {
        if(huecosJoyas == null){
            return new LinkedList<>();
        }
        return huecosJoyas;
    }

    public void setHuecosJoyas(List<Integer> huecosJoyas) {
        this.huecosJoyas = huecosJoyas;
    }

    public String getImagen() {
        if(imagen == null || imagen.isEmpty() || imagen == "" || imagen == "null"){
            return "default";
        }
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIcon() {
        if(icon == null || icon.isEmpty() || icon == "" || icon == "null"){
            return "icon";
        }
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTipo() {
        if(tipo == null){
            return "";
        }
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

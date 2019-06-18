package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Gear;

public class KinectInsect extends Gear {
    /**DATOS PARA LOS INSECTOS**/
    //TODO: Buscar las traducciones
    public static final String[] tiposInsecto = new String[]{"Sever", "Blunt"};
    //TODO: Buscar las traducciones
    public static final String[] dustEffects = new String[]{"Veneno", "Nitro", "Parálisis", "Curación"};
    public static final int[] statsPosibles = new int[]{1,2,3,4,5,6,7,8,9,10};
    //Aray asociativo
    public static final String[] stats = new String[]{"Elemento", "Poder", "Velocidad", "Curación"};


    /**Datos concretos de cada insecto**/
    public String tipoInsecto;
    //TODO: Buscar traduccion
    public String dustEffect;
    public int[] nivelStats;

    public KinectInsect(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public KinectInsect(String nombre, String tipo, String dustEffect, int[] stats){
        super(nombre);
        this.tipoInsecto = tipo;
        this.dustEffect = dustEffect;
        this.nivelStats = stats;
        this.setTipo(this.getClass().getSimpleName());
    }

    public int[] getNivelStats() {
        return nivelStats;
    }

    public void setNivelStats(int[] nivelStats) {
        this.nivelStats = nivelStats;
    }

    public String getTipo() {
        return tipoInsecto;
    }

    public void setTipo(String tipo) {
        this.tipoInsecto = tipo;
    }

    public String getDustEffect() {
        return dustEffect;
    }

    public void setDustEffect(String dustEffect) {
        this.dustEffect = dustEffect;
    }
}

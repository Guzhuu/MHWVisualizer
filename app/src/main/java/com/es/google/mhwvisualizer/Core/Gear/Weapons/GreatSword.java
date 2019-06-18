package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import android.content.res.Resources;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

import java.util.Map;

public class GreatSword extends Weapon{
    //Cantidad de afilado para rojo-naranja-amarillo-verde-azul-blanco-morado sobre 100
    public Map<Integer, Integer> afilado;

    public GreatSword(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public GreatSword(Weapon weapon){
        super();
        this.setTipo(this.getClass().getSimpleName());
        this.setNombre(weapon.getNombre());
        this.setRareza(weapon.getRareza());
        this.setDanho(weapon.getDanho());
        this.setDefensa(weapon.getDefensa());
        this.setElemento(weapon.getElemento());
        this.setElementoCantidad(weapon.getElementoCantidad());
        this.setElementoOculto(weapon.isElementoOculto());
        this.setAfinidad(weapon.getAfinidad());
        this.setSelloAncianos(weapon.getSelloAncianos());
        this.setHuecosJoyas(weapon.getHuecosJoyas());
        this.setMejoras(weapon.getMejoras());
        this.setID(weapon.getID());
        this.setIcon(weapon.getIcon());
        this.setImagen(weapon.getImagen());
    }

    public Map<Integer, Integer> getAfilado(){
        return this.afilado;
    }

    public void setAfilado(Map<Integer, Integer> afilado){
        this.afilado = afilado;
    }
}

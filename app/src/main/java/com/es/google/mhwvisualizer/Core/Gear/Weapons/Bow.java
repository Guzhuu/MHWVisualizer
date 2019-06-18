package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import android.content.res.Resources;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

import java.util.LinkedList;
import java.util.List;

public class Bow extends Weapon{

    public List<String> revestimientos;

    public Bow(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public Bow(Weapon weapon){
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

    public List<String> getRevestimientos() {
        if(revestimientos == null){
            return new LinkedList<String>();
        }
        return revestimientos;
    }

    public void setRevestimientos(List<String> revestimientos) {
        this.revestimientos = revestimientos;
    }


}

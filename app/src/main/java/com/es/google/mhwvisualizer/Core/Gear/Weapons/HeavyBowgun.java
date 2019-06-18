package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

public class HeavyBowgun extends Weapon{

    public String desvio;
    public String municionEspecial;

    //TODO: Tipo de munición y cosas

    public HeavyBowgun(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public HeavyBowgun(Weapon weapon){
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

    public String getMunicionEspecial() {
        if(this.municionEspecial == null || this.municionEspecial.isEmpty() || this.municionEspecial.equals("")){
            return "Sin municion especial";
        }
        return municionEspecial;
    }

    public String getDesvio(){
        if(this.desvio == null || this.desvio.isEmpty() || this.desvio.equals("")){
            return "Sin desvio";
        }
        return this.desvio;
    }

    public void setDesvio(String desvio) {
        this.desvio = desvio;
    }

    public void setMunicionEspecial(String municionEspecial) {
        this.municionEspecial = municionEspecial;
    }
}

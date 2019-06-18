package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

public class LightBowgun extends Weapon{

    public String desvio;

    //TODO: Tipo de munición y cosas

    public LightBowgun(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public LightBowgun(Weapon weapon){
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

    public String getDesvio() {
        if(this.desvio == null || this.desvio.isEmpty() || this.desvio.equals("")){
            return "Sin desvio";
        }
        return desvio;
    }

    public void setDesvio(String desvio) {
        this.desvio = desvio;
    }
}

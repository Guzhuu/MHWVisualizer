package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HuntingHorn extends Weapon {
    public List<String> notas;
    //Cantidad de afilado para rojo-naranja-amarillo-verde-azul-blanco-morado sobre 100
    public Map<Integer, Integer> afilado;
    public Map<Integer, Integer> afiladoMax;

    public HuntingHorn(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public HuntingHorn(Weapon weapon){
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

    public Map<Integer, Integer> getAfiladoMax(){
        return this.afiladoMax;
    }

    public void setAfiladoMax(Map<Integer, Integer> afiladoMax){
        this.afiladoMax = afiladoMax;
    }

    public List<String> getNotas() {
        if(this.notas == null){
            return new LinkedList<>();
        }
        return notas;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }
}
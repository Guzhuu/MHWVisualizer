package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

import java.util.Map;

public class DualBlades extends Weapon {
    //Cantidad de afilado para rojo-naranja-amarillo-verde-azul-blanco-morado sobre 100
    public Map<Integer, Integer> afilado;

    //Existe un arma única con 2 elementos
    public String elem2;
    public int elem2cantidad;


    public DualBlades(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public DualBlades(Weapon weapon){
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

    public int getElem2cantidad() {
        return elem2cantidad;
    }

    public void setElem2cantidad(int elem2cantidad) {
        this.elem2cantidad = elem2cantidad;
    }

    public Map<Integer, Integer> getAfilado(){
        return this.afilado;
    }

    public void setAfilado(Map<Integer, Integer> afilado){
        this.afilado = afilado;
    }

    public String getElem2(){
        if(this.elem2 == null || elem2 == ""){
            return "No";
        }
        return this.elem2;
    }

    public boolean hasElementoSecundario(){
        if(this.elem2 != null || !this.elem2.equals("") || this.getElem2().equals("No")){
            return false;
        }else{
            return true;
        }
    }

    public void setElem2(String elem2){
        this.elem2 = elem2;
    }
}

package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

import java.util.HashMap;
import java.util.Map;

public class GunLance extends Weapon {
    public String proyectil;
    public int lvlProyectil;

    public static final Map<String, String> proyectilMap = new HashMap<>();

    //Cantidad de afilado para rojo-naranja-amarillo-verde-azul-blanco-morado sobre 100
    public Map<Integer, Integer> afilado;

    public GunLance(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public GunLance(Weapon weapon){
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

    public String getProyectil(){
        if(proyectilMap.isEmpty()){
            proyectilMap.put("Long", "Larga");
            proyectilMap.put("Wide", "Abanico");
            proyectilMap.put("Normal", "Normal");
        }
        if(proyectilMap.containsKey(this.proyectil)){
            return proyectilMap.get(this.proyectil);
        }else if(proyectilMap.values().contains(this.proyectil)) {
            return this.proyectil;
        }else{
            return "No encontrado";
        }
    }

    public void setProyectil(String proyectil){
        this.proyectil = proyectil;
    }

    public int getLvlProyectil() {
        return lvlProyectil;
    }

    public void setLvlProyectil(int lvlProyectil) {
        this.lvlProyectil = lvlProyectil;
    }
}
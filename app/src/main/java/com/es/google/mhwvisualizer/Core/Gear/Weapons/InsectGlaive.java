package com.es.google.mhwvisualizer.Core.Gear.Weapons;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;

import java.util.HashMap;
import java.util.Map;

public class InsectGlaive extends Weapon {
    public static final Map<String, String> bonusInsectoMap = new HashMap<>();

    public String bonusInsecto;
    //Cantidad de afilado para rojo-naranja-amarillo-verde-azul-blanco-morado sobre 100
    public Map<Integer, Integer> afilado;

    public InsectGlaive(){
        super();
        this.setTipo(this.getClass().getSimpleName());
    }

    public InsectGlaive(Weapon weapon){
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

    public String getBonusInsecto() {
        if(bonusInsectoMap.isEmpty()){
            bonusInsectoMap.put("sever","Corte");
            bonusInsectoMap.put("speed","Velocidad");
            bonusInsectoMap.put("element","Elemental");
            bonusInsectoMap.put("health","Salud");
            bonusInsectoMap.put("stamina","Resistencia");
            bonusInsectoMap.put("blunt","Impacto");
        }
        if(bonusInsectoMap.containsKey(this.bonusInsecto)){
            return bonusInsectoMap.get(this.bonusInsecto);
        }else if(bonusInsectoMap.values().contains(this.bonusInsecto)) {
            return this.bonusInsecto;
        }else{
            return "No encontrado";
        }
    }

    public void setBonusInsecto(String bonusInsecto) {
        this.bonusInsecto = bonusInsecto;
    }
}
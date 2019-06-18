package com.es.google.mhwvisualizer.UI;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.google.mhwvisualizer.Core.Gear.Gear;
import com.es.google.mhwvisualizer.R;

import java.util.HashMap;

/**ADAPTADOR PARA gear_weapon_type_button**/
public class GearTypeButtonAdapter extends ArrayAdapter<Gear> {

    public GearTypeButtonAdapter(Context context, Gear[] objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final LayoutInflater layoutInflater = LayoutInflater.from( this.getContext() );
        final Gear gear = this.getItem( position );
        if ( convertView == null ) {
            convertView = layoutInflater.inflate( R.layout.list_gear_type_button, null );
        }

        final TextView tvTipoArma = convertView.findViewById(R.id.tvTipoArma);
        final ImageView ivTipoArma = convertView.findViewById(R.id.ivTipoArma);

        tvTipoArma.setText(gear.getNombre());
        switch(gear.getImagen()){
            case "BOW":
                ivTipoArma.setImageResource(R.drawable.bow);
                break;

            case "CHARGEBLADE":
                ivTipoArma.setImageResource(R.drawable.chargeblade);
                break;

            case "DUALBLADES":
                ivTipoArma.setImageResource(R.drawable.dualblades);
                break;

            case "GREATSWORD":
                ivTipoArma.setImageResource(R.drawable.greatsword);
                break;

            case "GUNLANCE":
                ivTipoArma.setImageResource(R.drawable.gunlance);
                break;

            case "HAMMER":
                ivTipoArma.setImageResource(R.drawable.hammer);
                break;

            case "HEAVYBOWGUN":
                ivTipoArma.setImageResource(R.drawable.heavybowgun);
                break;

            case "HUNTINGHORN":
                ivTipoArma.setImageResource(R.drawable.huntinghorn);
                break;

            case "INSECTGLAIVE":
                ivTipoArma.setImageResource(R.drawable.insectglaive);
                break;

            case "LANCE":
                ivTipoArma.setImageResource(R.drawable.lance);
                break;

            case "LIGHTBOWGUN":
                ivTipoArma.setImageResource(R.drawable.lightbowgun);
                break;

            case "LONGSWORD":
                ivTipoArma.setImageResource(R.drawable.longsword);
                break;

            case "SWITCHAXE":
                ivTipoArma.setImageResource(R.drawable.switchaxe);
                break;

            case "SWORDANDSHIELD":
                ivTipoArma.setImageResource(R.drawable.swordnshield);
                break;

            case "KINECTINSECT":
                ivTipoArma.setImageResource(R.drawable.insectglaive);
                break;
        }

        return convertView;
    }
}

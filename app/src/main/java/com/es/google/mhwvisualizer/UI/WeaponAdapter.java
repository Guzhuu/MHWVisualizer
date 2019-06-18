package com.es.google.mhwvisualizer.UI;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;
import com.es.google.mhwvisualizer.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**ADAPTADOR PARA list_weapon**/
public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.ViewHolder> {
    public String LOGTAG = "WEAPONS_ADAPTER";
    private List<Weapon> armas;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private boolean iconos;
    private float DENSITY_DPI;
    private float DENSITY_DEFAULT;
    
    public static final int DEFAULT = 0;
    public static final int ALFABETICO = 1;
    public static final int RAREZA = 2;

    WeaponAdapter(Context context, List<Weapon> armas, boolean iconos, int orden) {
        this.inflater = LayoutInflater.from(context);
        this.armas = armas;
        this.iconos = iconos;
        ordenar(orden);
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        DENSITY_DPI = metrics.densityDpi;
        DENSITY_DEFAULT =  DisplayMetrics.DENSITY_DEFAULT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.list_weapon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.armas.size();
    }

    public Weapon getItem(int id) {
        return this.armas.get(id);
    }

    public void switchImgs(){
        this.iconos = !this.iconos;
    }

    public void ordenar(int modo){
        switch(modo){
            case ALFABETICO:
                Collections.sort(armas, new Comparator<Weapon>() {
                    public int compare(Weapon c1, Weapon c2) {
                        return c1.getNombre().compareTo(c2.getNombre());
                    }});
                break;
            case RAREZA:
                Collections.sort(armas, new Comparator<Weapon>() {
                    public int compare(Weapon c1, Weapon c2) {
                        if(c1.getRareza() < c2.getRareza()) return -1;
                        if(c1.getRareza() > c2.getRareza()) return 1;
                        return 0;
                    }});
                break;
            case DEFAULT: default:
                Collections.sort(armas, new Comparator<Weapon>() {
                    public int compare(Weapon c1, Weapon c2) {
                        if (c1.getID() > c2.getID()) return 1;
                        if (c1.getID() < c2.getID()) return -1;
                        return 0;
                    }});
                break;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Weapon arma = this.armas.get(i);
        Log.d(LOGTAG, "Nombre: " + arma.getNombre());
        holder.tvNombreArma.setText(arma.getNombre());
        ImageView ivIconArma = holder.ivIconArma;
        if(iconos){
            ivIconArma.getLayoutParams().height =  (int) convertDpToPixel(100f);
            ivIconArma.getLayoutParams().width  =  (int) convertDpToPixel(100f);
            ivIconArma.requestLayout();
            Log.d(LOGTAG, "Icono: " + arma.getIcon());
            Picasso.get().load(arma.getIcon()).into(ivIconArma);
        }else{
            ivIconArma.getLayoutParams().height =  (int) convertDpToPixel(180f);
            ivIconArma.getLayoutParams().width  =  (int) convertDpToPixel(180f);
            ivIconArma.requestLayout();
            Log.d(LOGTAG, "Imagen: " + arma.getImagen());
            Picasso.get().load(arma.getImagen()).into(ivIconArma);
        }
    }

    public float convertDpToPixel(float dp){
        return dp * (DENSITY_DPI / DENSITY_DEFAULT);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNombreArma;
        ImageView ivIconArma;

        ViewHolder(View itemView) {
            super(itemView);
            tvNombreArma = itemView.findViewById(R.id.tvNombreArma);
            ivIconArma = itemView.findViewById(R.id.ivIconArma);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null){
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

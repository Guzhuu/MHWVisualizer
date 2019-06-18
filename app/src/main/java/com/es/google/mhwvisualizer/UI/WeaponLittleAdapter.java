package com.es.google.mhwvisualizer.UI;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.DualBlades;
import com.es.google.mhwvisualizer.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**ADAPTADOR PARA list_weapon**/
public class WeaponLittleAdapter extends RecyclerView.Adapter<WeaponLittleAdapter.ViewHolder> {
    public String LOGTAG = "WEAPONS_LITTLE_ADAPTER";
    private List<Weapon> armas;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private Context ctx;
    private float DENSITY_DPI;
    private float DENSITY_DEFAULT;

    WeaponLittleAdapter(Context context, List<Weapon> armas) {
        this.inflater = LayoutInflater.from(context);
        this.armas = armas;
        this.ctx = context;
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        DENSITY_DPI = metrics.densityDpi;
        DENSITY_DEFAULT =  DisplayMetrics.DENSITY_DEFAULT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.list_weapon_little, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.armas.size();
    }

    public Weapon getItem(int id) {
        return this.armas.get(id);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Weapon arma = this.armas.get(i);
        TextView tvNombreArma = holder.tvNombreArma;
        TextView tvDanho = holder.tvDanho;
        TextView tvElemento = holder.tvElemento;
        TextView tvElemento2 = holder.tvElemento2;
        ImageView ivImgArma = holder.ivImgArma;
        ImageView ivElemento = holder.ivElemento;
        ImageView ivElemento2 = holder.ivElemento2;
        ImageView ivRarezaArma = holder.ivRarezaArma;

        if(arma instanceof DualBlades){
            if(((DualBlades) arma).hasElementoSecundario()){
                ivElemento2.setVisibility(View.VISIBLE);
                tvElemento2.setVisibility(View.VISIBLE);
                setElemento(((DualBlades) arma).getElem2(), ((DualBlades) arma).getElem2cantidad(), arma.isElementoOculto(), tvElemento2, ivElemento2);
            }else{
                ivElemento2.setVisibility(View.GONE);
                tvElemento2.setVisibility(View.GONE);
            }
        }else{
            ivElemento2.setVisibility(View.GONE);
            tvElemento2.setVisibility(View.GONE);
        }
        if(arma != null){
            Picasso.get().load(arma.getImagen()).into(ivImgArma);
            Picasso.get().load(arma.getIcon()).into(ivRarezaArma);
            tvDanho.setText(Integer.toString(arma.getDanho()));
            tvNombreArma.setText(arma.getNombre());
            setElemento(arma.getElemento(), arma.getElementoCantidad(), arma.isElementoOculto(), tvElemento, ivElemento);
        }else{
            ivImgArma.setImageResource(R.drawable.weapon);
        }
    }

    public float convertDpToPixel(float dp){
        return dp * (DENSITY_DPI / DENSITY_DEFAULT);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNombreArma;
        TextView tvDanho;
        TextView tvElemento;
        TextView tvElemento2;
        ImageView ivImgArma;
        ImageView ivElemento;
        ImageView ivElemento2;
        ImageView ivRarezaArma;

        ViewHolder(View itemView) {
            super(itemView);
            tvNombreArma = itemView.findViewById(R.id.tvNombreArma);
            tvDanho = itemView.findViewById(R.id.tvDanho);
            tvElemento = itemView.findViewById(R.id.tvElemento);
            tvElemento2 = itemView.findViewById(R.id.tvElemento2);
            ivImgArma = itemView.findViewById(R.id.ivImgArma);
            ivElemento2 = itemView.findViewById(R.id.ivElemento2);
            ivElemento = itemView.findViewById(R.id.ivElemento);
            ivRarezaArma = itemView.findViewById(R.id.ivRarezaArma);
            ivElemento.getLayoutParams().height     =  (int) convertDpToPixel(20f);
            ivElemento.getLayoutParams().width      =  (int) convertDpToPixel(20f);
            ivElemento2.getLayoutParams().height    =  (int) convertDpToPixel(20f);
            ivElemento2.getLayoutParams().width     =  (int) convertDpToPixel(20f);
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

    public void setElemento(String elemento, int elementoCantidad, boolean elementoOculto, TextView tv, ImageView iv){
        tv.setText(Integer.toString(elementoCantidad));
        switch(elemento){
            case Weapon.FUEGO:
                iv.setImageResource(R.drawable.fuego);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.fuego));
                break;
            case Weapon.AGUA:
                iv.setImageResource(R.drawable.agua);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.agua));
                break;
            case Weapon.TRUENO:
                iv.setImageResource(R.drawable.trueno);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.trueno));
                break;
            case Weapon.HIELO:
                iv.setImageResource(R.drawable.hielo);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.hielo));
                break;
            case Weapon.DRAGON:
                iv.setImageResource(R.drawable.dragon);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.dragon));
                break;
            case Weapon.VENENO:
                iv.setImageResource(R.drawable.veneno);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.veneno));
                break;
            case Weapon.SUENHO:
                iv.setImageResource(R.drawable.suenho);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.suenho));
                break;
            case Weapon.PARALISIS:
                iv.setImageResource(R.drawable.paralisis);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.paralisis));
                break;
            case Weapon.NITRO:
                iv.setImageResource(R.drawable.nitro);
                tv.setTextColor(ContextCompat.getColor(ctx, R.color.nitro));
                break;
            default:
                iv.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                break;
        }
        if(elementoOculto){
            //Si es elemento oculto, canal alpha a la mitad
            iv.setAlpha(0.4f);
            tv.setAlpha(0.4f);
            tv.setTextColor(ContextCompat.getColor(ctx, R.color.elementoculto));
        }
    }
}

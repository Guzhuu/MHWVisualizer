package com.es.google.mhwvisualizer.UI;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Bow;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.ChargeBlade;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.DualBlades;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.GreatSword;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.GunLance;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Hammer;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.HeavyBowgun;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.HuntingHorn;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.InsectGlaive;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.Lance;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.LightBowgun;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.LongSword;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.SwitchAxe;
import com.es.google.mhwvisualizer.Core.Gear.Weapons.SwordandShield;
import com.es.google.mhwvisualizer.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeaponActivity extends AppCompatActivity implements WeaponLittleAdapter.ItemClickListener {
    public static final String LOGTAG = "WEAPON_ACTIVITY";
    public int IDArma;

    private static final String BOW = "Bow";
    private static final String CHARGEBLADE = "ChargeBlade";
    private static final String DUALBLADES = "DualBlades";
    private static final String GREATSWORD = "GreatSword";
    private static final String LONGSWORD = "LongSword";
    private static final String SWORDANDSHIELD = "SwordandShield";
    private static final String HAMMER = "Hammer";
    private static final String HEAVYBOWGUN = "HeavyBowgun";
    private static final String HUNTINGHORN = "HuntingHorn";
    private static final String SWITCHAXE = "SwitchAxe";
    private static final String LIGHTBOWGUN = "LightBowgun";
    private static final String LANCE = "Lance";
    private static final String GUNLANCE = "GunLance";
    private static final String INSECTGLAIVE = "InsectGlaive";

    private static final String NOTABLANCA = "nota_blanca";
    private static final String NOTAROJA = "nota_roja";
    private static final String NOTANARANJA = "nota_naranja";
    private static final String NOTAMARILLA = "nota_amarilla";
    private static final String NOTAVERDE = "nota_verde";
    private static final String NOTAANHIL = "nota_anhil";
    private static final String NOTAAZUL = "nota_azul";
    private static final String NOTAMORADA = "nota_morada";

    public static final List<Paint> paintList = new LinkedList<>();


    private Weapon weaponError;
    private TextView tvNombreArma;
    private ImageView ivImagenArma;
    private TextView tvRareza;
    private ImageView ivRareza;
    private TextView tvDanho;
    private TextView lblElemento;
    private TextView tvElemento;
    private ImageView ivElemento;
    private TextView tvElementoSecundario;
    private ImageView ivElementoSecundario;
    private TextView tvAfinidad;
    private TextView lblAfilado;
    private ImageView ivAfilado;
    private ImageView ivAfiladoMax;
    private TextView tvSelloAncianos;
    private TextView tvDefensa;
    private TextView lblBonusInsecto;
    private TextView tvBonusInsecto;
    private TextView lblDesvio;
    private TextView tvDesvio;
    private TextView lblProyectil;
    private TextView tvProyectilTipo;
    private TextView tvProyectilLvl;
    private TextView lblMunicionEspecial;
    private TextView tvMunicionEspecial;
    private ImageView ivSlot1;
    private ImageView ivSlot2;
    private ImageView ivSlot3;
    private TextView lblRevestimiento;
    private ImageView ivRevPoder;
    private ImageView ivRevCortoAlcance;
    private ImageView ivRevParalisis;
    private ImageView ivRevNitro;
    private ImageView ivRevSuenho;
    private ImageView ivRevVeneno;
    private TextView lblNotas;
    private ImageView ivNotaBlanca;
    private ImageView ivNotaRoja;
    private ImageView ivNotaNaranja;
    private ImageView ivNotaAmarilla;
    private ImageView ivNotaVerde;
    private ImageView ivNotaAnhil;
    private ImageView ivNotaAzul;
    private ImageView ivNotaMorada;
    private RecyclerView rvMejoras;

    private WeaponLittleAdapter mejorasAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_weapon_detail );

        tvNombreArma = findViewById(R.id.tvNombreArma);
        ivImagenArma = findViewById(R.id.ivImgArma);
        tvRareza = findViewById(R.id.tvRareza);
        ivRareza = findViewById(R.id.ivRareza);
        tvDanho = findViewById(R.id.tvDanho);
        lblElemento = findViewById(R.id.lblElemento);
        tvElemento = findViewById(R.id.tvElemento);
        ivElemento = findViewById(R.id.ivElemento);
        tvElementoSecundario = findViewById(R.id.tvElementoSecundario);
        ivElementoSecundario = findViewById(R.id.ivElementoSecundario);
        tvAfinidad = findViewById(R.id.tvAfinidad);
        lblAfilado = findViewById(R.id.lblAfilado);
        ivAfilado = findViewById(R.id.ivAfilado);
        ivAfiladoMax = findViewById(R.id.ivAfiladoMax);
        tvSelloAncianos = findViewById(R.id.tvSelloAncianos);
        tvDefensa = findViewById(R.id.tvDefensa);
        lblBonusInsecto = findViewById(R.id.lblBonusInsecto);
        tvBonusInsecto = findViewById(R.id.tvBonusInsecto);
        lblDesvio = findViewById(R.id.lblDesvio);
        tvDesvio = findViewById(R.id.tvDesvio);
        lblProyectil = findViewById(R.id.lblProyectil);
        tvProyectilTipo = findViewById(R.id.tvProyectilTipo);
        tvProyectilLvl = findViewById(R.id.tvProyectilLvl);
        lblMunicionEspecial = findViewById(R.id.lblMunicionEspecial);
        tvMunicionEspecial = findViewById(R.id.tvMunicionEspecial);
        ivSlot1 = findViewById(R.id.ivSlots1);
        ivSlot2 = findViewById(R.id.ivSlots2);
        ivSlot3 = findViewById(R.id.ivSlots3);
        lblRevestimiento = findViewById(R.id.lblRevestimiento);
        ivRevPoder = findViewById(R.id.ivRevPoder);
        ivRevCortoAlcance = findViewById(R.id.ivRevCortoAlcance);
        ivRevNitro = findViewById(R.id.ivRevNitro);
        ivRevParalisis = findViewById(R.id.ivRevParalisis);
        ivRevSuenho = findViewById(R.id.ivRevSuenho);
        ivRevVeneno = findViewById(R.id.ivRevVeneno);
        lblNotas = findViewById(R.id.lblNotas);
        ivNotaBlanca = findViewById(R.id.ivNotaBlanca);
        ivNotaRoja = findViewById(R.id.ivNotaRoja);
        ivNotaNaranja = findViewById(R.id.ivNotaNaranja);
        ivNotaAmarilla = findViewById(R.id.ivNotaAmarilla);
        ivNotaVerde = findViewById(R.id.ivNotaVerde);
        ivNotaAnhil = findViewById(R.id.ivNotaAnhil);
        ivNotaAzul = findViewById(R.id.ivNotaAzul);
        ivNotaMorada = findViewById(R.id.ivNotaMorada);
        rvMejoras = findViewById(R.id.rvMejoras);


        Intent datosEnviados = this.getIntent();
        IDArma = datosEnviados.getExtras().getInt(App.WEAPON);
        Log.d(LOGTAG, "Mostrando " + IDArma);
        if(App.weaponDetail != null && IDArma == App.weaponDetail.getID()){
            this.weaponError = null;
            this.detailWeapon(App.weaponDetail);
        }else{
            //Recuperar de la base de datos el arma con IDArma
            this.detailWeapon(weaponError);
        }
    }

    public void detailWeapon(Weapon weapon){
        if(weapon != null){
            tvNombreArma.setText(weapon.getNombre());
            Picasso.get().load(weapon.getImagen()).into(ivImagenArma);

            setRareza(weapon.getRareza());
            Picasso.get().load(weapon.getIcon()).into(ivRareza);

            tvDanho.setText(Integer.toString(weapon.getDanho()));

            setElemento(weapon.getElemento(), weapon.getElementoCantidad(), weapon.isElementoOculto(), tvElemento, ivElemento);

            tvAfinidad.setText(weapon.getAfinidad() + "% ");

            tvSelloAncianos.setText(weapon.getSelloAncianos());

            tvDefensa.setText(Integer.toString(weapon.getDefensa()));

            setHuecos(weapon.getHuecosJoyas());

            setMejoras(weapon);
            switch(weapon.getTipo()){
                case BOW:
                    setRevestimientos(((Bow) weapon).getRevestimientos());
                    setAfilado(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.GONE);
                    tvDesvio.setVisibility(View.GONE);
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setNotas(null);
                    break;

                case CHARGEBLADE: case GREATSWORD: case HAMMER: case LANCE: case LONGSWORD: case SWITCHAXE: case SWORDANDSHIELD:
                    setRevestimientos(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.GONE);
                    tvDesvio.setVisibility(View.GONE);
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setNotas(null);
                    setAfilado(weapon);
                    break;

                case DUALBLADES:
                    setRevestimientos(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.GONE);
                    tvDesvio.setVisibility(View.GONE);
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    setAfilado(weapon);
                    if(((DualBlades) weapon).hasElementoSecundario()){
                        tvElementoSecundario.setVisibility(View.VISIBLE);
                        ivElementoSecundario.setVisibility(View.VISIBLE);
                        setElemento(((DualBlades) weapon).getElem2(), ((DualBlades) weapon).getElem2cantidad(), weapon.isElementoOculto(), tvElementoSecundario, ivElementoSecundario);
                    }else{
                        tvElementoSecundario.setVisibility(View.GONE);
                        ivElementoSecundario.setVisibility(View.GONE);
                    }
                    setNotas(null);
                    break;

                case GUNLANCE:
                    setRevestimientos(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.GONE);
                    tvDesvio.setVisibility(View.GONE);
                    lblProyectil.setVisibility(View.VISIBLE);
                    tvProyectilLvl.setVisibility(View.VISIBLE);
                    tvProyectilLvl.setText(Integer.toString(((GunLance) weapon).getLvlProyectil()));
                    tvProyectilTipo.setVisibility(View.VISIBLE);
                    tvProyectilTipo.setText(((GunLance) weapon).getProyectil());
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setNotas(null);
                    setAfilado(weapon);
                    break;

                case HEAVYBOWGUN:
                    setRevestimientos(null);
                    setAfilado(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.VISIBLE);
                    tvDesvio.setVisibility(View.VISIBLE);
                    tvMunicionEspecial.setText(((HeavyBowgun) weapon).getDesvio());
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.VISIBLE);
                    tvMunicionEspecial.setVisibility(View.VISIBLE);
                    tvMunicionEspecial.setText(((HeavyBowgun) weapon).getMunicionEspecial());
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setNotas(null);
                    break;

                case HUNTINGHORN:
                    setRevestimientos(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.GONE);
                    tvDesvio.setVisibility(View.GONE);
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setAfilado(weapon);
                    setNotas(((HuntingHorn) weapon).getNotas());
                    break;

                case INSECTGLAIVE:
                    setRevestimientos(null);
                    lblBonusInsecto.setVisibility(View.VISIBLE);
                    tvBonusInsecto.setVisibility(View.VISIBLE);
                    tvMunicionEspecial.setText(((InsectGlaive) weapon).getBonusInsecto());
                    lblDesvio.setVisibility(View.GONE);
                    tvDesvio.setVisibility(View.GONE);
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setNotas(null);
                    setAfilado(weapon);
                    break;

                case LIGHTBOWGUN:
                    setRevestimientos(null);
                    setAfilado(null);
                    lblBonusInsecto.setVisibility(View.GONE);
                    tvBonusInsecto.setVisibility(View.GONE);
                    lblDesvio.setVisibility(View.VISIBLE);
                    tvDesvio.setVisibility(View.VISIBLE);
                    tvMunicionEspecial.setText(((LightBowgun) weapon).getDesvio());
                    lblProyectil.setVisibility(View.GONE);
                    tvProyectilLvl.setVisibility(View.GONE);
                    tvProyectilTipo.setVisibility(View.GONE);
                    lblMunicionEspecial.setVisibility(View.GONE);
                    tvMunicionEspecial.setVisibility(View.GONE);
                    tvElementoSecundario.setVisibility(View.GONE);
                    ivElementoSecundario.setVisibility(View.GONE);
                    setNotas(null);
                    break;

                default:
                    break;
            }
        }
    }

    public void setElemento(String elemento, int cantidad, boolean elementoOculto, TextView tv, ImageView iv){
        lblElemento.setVisibility(View.VISIBLE);
        iv.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
        tv.setText(Integer.toString(cantidad));
        switch(elemento){
            case Weapon.FUEGO:
                iv.setImageResource(R.drawable.fuego);
                tv.setTextColor(ContextCompat.getColor(this, R.color.fuego));
                break;
            case Weapon.AGUA:
                iv.setImageResource(R.drawable.agua);
                tv.setTextColor(ContextCompat.getColor(this, R.color.agua));
                break;
            case Weapon.TRUENO:
                iv.setImageResource(R.drawable.trueno);
                tv.setTextColor(ContextCompat.getColor(this, R.color.trueno));
                break;
            case Weapon.HIELO:
                iv.setImageResource(R.drawable.hielo);
                tv.setTextColor(ContextCompat.getColor(this, R.color.hielo));
                break;
            case Weapon.DRAGON:
                iv.setImageResource(R.drawable.dragon);
                tv.setTextColor(ContextCompat.getColor(this, R.color.dragon));
                break;
            case Weapon.VENENO:
                iv.setImageResource(R.drawable.veneno);
                tv.setTextColor(ContextCompat.getColor(this, R.color.veneno));
                break;
            case Weapon.SUENHO:
                iv.setImageResource(R.drawable.suenho);
                tv.setTextColor(ContextCompat.getColor(this, R.color.suenho));
                break;
            case Weapon.PARALISIS:
                iv.setImageResource(R.drawable.paralisis);
                tv.setTextColor(ContextCompat.getColor(this, R.color.paralisis));
                break;
            case Weapon.NITRO:
                iv.setImageResource(R.drawable.nitro);
                tv.setTextColor(ContextCompat.getColor(this, R.color.nitro));
                break;
            default:
                lblElemento.setVisibility(View.GONE);
                iv.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                break;
        }
        if(elementoOculto){
            //Si es elemento oculto, canal alpha a la mitad
            iv.setAlpha(0.4f);
            tv.setAlpha(0.4f);
            tv.setTextColor(ContextCompat.getColor(this, R.color.elementoculto));
        }else{
            iv.setAlpha(1f);
            tv.setAlpha(1f);
        }
    }

    public void setHuecos(List<Integer> huecos){
        int i = 0;
        int drawable;
        for(Integer hueco : huecos){
            if(hueco == 1){
                drawable = R.drawable.slot1;
            }else if(hueco == 2){
                drawable = R.drawable.slot2;
            }else{
                drawable = R.drawable.slot3;
            }

            if(i == 0){
                ivSlot1.setImageResource(drawable);
                i++;
            }else if(i == 1){
                ivSlot2.setImageResource(drawable);
                i++;
            }else{
                ivSlot3.setImageResource(drawable);
                i++;
            }
        }

        drawable = R.drawable.slot0;
        while(i < 3){
            if(i == 0){
                ivSlot1.setImageResource(drawable);
                i++;
            }else if(i == 1){
                ivSlot2.setImageResource(drawable);
                i++;
            }else{
                ivSlot3.setImageResource(drawable);
                i++;
            }
        }
    }

    public void setRevestimientos(List<String> revestimientos){
        if(revestimientos == null){
            lblRevestimiento.setVisibility(View.INVISIBLE);
            ivRevCortoAlcance.setVisibility(View.GONE);
            ivRevPoder.setVisibility(View.GONE);
            ivRevSuenho.setVisibility(View.GONE);
            ivRevParalisis.setVisibility(View.GONE);
            ivRevNitro.setVisibility(View.GONE);
            ivRevVeneno.setVisibility(View.GONE);
        }else{
            if(revestimientos.contains(Weapon.CORTOALCANCE)){
                ivRevCortoAlcance.setVisibility(View.VISIBLE);
                ivRevCortoAlcance.setVisibility(View.INVISIBLE);
            }
            if(revestimientos.contains(Weapon.PODER)){
                ivRevPoder.setVisibility(View.VISIBLE);
            }else{
                ivRevPoder.setVisibility(View.INVISIBLE);
            }
            if(revestimientos.contains(Weapon.SUENHO)){
                ivRevSuenho.setVisibility(View.VISIBLE);
            }else{
                ivRevSuenho.setVisibility(View.INVISIBLE);
            }
            if(revestimientos.contains(Weapon.PARALISIS)){
                ivRevParalisis.setVisibility(View.VISIBLE);
            }else{
                ivRevParalisis.setVisibility(View.INVISIBLE);
            }
            if(revestimientos.contains(Weapon.NITRO)){
                ivRevNitro.setVisibility(View.VISIBLE);
            }else{
                ivRevNitro.setVisibility(View.INVISIBLE);
            }
            if(revestimientos.contains(Weapon.VENENO)){
                ivRevVeneno.setVisibility(View.VISIBLE);
            }else{
                ivRevVeneno.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setNotas(List<String> notas){
        lblNotas.setVisibility(View.GONE);
        ivNotaMorada.setVisibility(View.INVISIBLE);
        ivNotaAmarilla.setVisibility(View.INVISIBLE);
        ivNotaRoja.setVisibility(View.INVISIBLE);
        ivNotaBlanca.setVisibility(View.INVISIBLE);
        ivNotaVerde.setVisibility(View.INVISIBLE);
        ivNotaAnhil.setVisibility(View.INVISIBLE);
        ivNotaAzul.setVisibility(View.INVISIBLE);
        ivNotaNaranja.setVisibility(View.INVISIBLE);
        if(notas != null){
            lblNotas.setVisibility(View.VISIBLE);
            for(String nota : notas){
                switch(nota){
                    case NOTABLANCA:
                        ivNotaBlanca.setVisibility(View.VISIBLE);
                        break;
                    case NOTAROJA:
                        ivNotaRoja.setVisibility(View.VISIBLE);
                        break;
                    case NOTANARANJA:
                        ivNotaNaranja.setVisibility(View.VISIBLE);
                        break;
                    case NOTAMARILLA:
                        ivNotaAmarilla.setVisibility(View.VISIBLE);
                        break;
                    case NOTAVERDE:
                        ivNotaVerde.setVisibility(View.VISIBLE);
                        break;
                    case NOTAANHIL:
                        ivNotaAnhil.setVisibility(View.VISIBLE);
                        break;
                    case NOTAAZUL:
                        ivNotaAzul.setVisibility(View.VISIBLE);
                        break;
                    case NOTAMORADA:
                        ivNotaMorada.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void setRareza(int rareza){
        tvRareza.setText(Integer.toString(rareza));
        switch (rareza){
            case 1:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza1));
                break;
            case 2:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza2));
                break;
            case 3:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza3));
                break;
            case 4:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza4));
                break;
            case 5:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza5));
                break;
            case 6:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza6));
                break;
            case 7:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza7));
                break;
            case 8:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rareza8));
                break;
            default:
                tvRareza.setTextColor(ContextCompat.getColor(this, R.color.rarezaX));
                break;
        }
    }

    public void setAfilado(Weapon weapon){
        if(weapon != null){
            Map<Integer, Integer> afilado;
            Map<Integer, Integer> afiladoMax;

            lblAfilado.setVisibility(View.VISIBLE);
            ivAfilado.setVisibility(View.VISIBLE);
            ivAfiladoMax.setVisibility(View.VISIBLE);
            switch(weapon.getClass().getSimpleName()){
                case CHARGEBLADE:
                    afilado = ((ChargeBlade) weapon).getAfilado();
                    afiladoMax = ((ChargeBlade) weapon).getAfiladoMax();
                    break;

                case GREATSWORD:
                    afilado = ((GreatSword) weapon).getAfilado();
                    afiladoMax = ((GreatSword) weapon).getAfiladoMax();
                    break;

                case HAMMER:
                    afilado = ((Hammer) weapon).getAfilado();
                    afiladoMax = ((Hammer) weapon).getAfiladoMax();
                    break;

                case LANCE:
                    afilado = ((Lance) weapon).getAfilado();
                    afiladoMax = ((Lance) weapon).getAfiladoMax();
                    break;

                case LONGSWORD:
                    afilado = ((LongSword) weapon).getAfilado();
                    afiladoMax = ((LongSword) weapon).getAfiladoMax();
                    break;

                case SWITCHAXE:
                    afilado = ((SwitchAxe) weapon).getAfilado();
                    afiladoMax = ((SwitchAxe) weapon).getAfiladoMax();
                    break;

                case SWORDANDSHIELD:
                    afilado = ((SwordandShield) weapon).getAfilado();
                    afiladoMax = ((SwordandShield) weapon).getAfiladoMax();
                    break;

                case DUALBLADES:
                    afilado = ((DualBlades) weapon).getAfilado();
                    afiladoMax = ((DualBlades) weapon).getAfiladoMax();
                    break;

                case GUNLANCE:
                    afilado = ((GunLance) weapon).getAfilado();
                    afiladoMax = ((GunLance) weapon).getAfiladoMax();
                    break;

                case HUNTINGHORN:
                    afilado = ((HuntingHorn) weapon).getAfilado();
                    afiladoMax = ((HuntingHorn) weapon).getAfiladoMax();
                    break;

                case INSECTGLAIVE:
                    afilado = ((InsectGlaive) weapon).getAfilado();
                    afiladoMax = ((InsectGlaive) weapon).getAfiladoMax();
                    break;

                default:
                    lblAfilado.setVisibility(View.GONE);
                    ivAfilado.setVisibility(View.GONE);
                    ivAfiladoMax.setVisibility(View.GONE);
                    return;
            }

            Log.d(LOGTAG, "Afilados de " + weapon.getID() + ": " + afilado.values().toString());
            Log.d(LOGTAG, "Afilados max de " + weapon.getID() + ": " + afiladoMax.values().toString());
            if(afilado.keySet().size() >= 6 && afiladoMax.keySet().size() >= 6){
                if(paintList == null || paintList.isEmpty()){
                    setPaint();
                }

                int width = (int) convertDpToPixel(448f);
                int height = (int) convertDpToPixel(20f);

                Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                Bitmap bmp = Bitmap.createBitmap(width, height, conf);
                Canvas canvas = new Canvas(bmp);
                Rect rect = new Rect(0,0,(int) convertDpToPixel(448f),(int) convertDpToPixel(20f));

                canvas.drawRect(rect, paintList.get(6));
                int margenIzq = 0;
                //El keyset es de 0 a 5
                for(int i = 0; i < 6; i++){
                    rect = new Rect(margenIzq,(int) convertDpToPixel(3f),(int) convertDpToPixel((afilado.get(i)*448f)/100) + margenIzq,(int) convertDpToPixel(16f));
                    margenIzq += (int) convertDpToPixel((afilado.get(i)*448f)/100);
                    canvas.drawRect(rect, paintList.get(i));
                }

                ivAfilado.setImageBitmap(bmp);

                bmp = Bitmap.createBitmap(width, height, conf);
                canvas = new Canvas(bmp);
                rect = new Rect(0,0,(int) convertDpToPixel(448f),(int) convertDpToPixel(20f));

                canvas.drawRect(rect, paintList.get(6));
                margenIzq = 0;
                //El keyset es de 0 a 5
                for(int i = 0; i < 6; i++){
                    rect = new Rect(margenIzq,(int) convertDpToPixel(3f),(int) convertDpToPixel((afiladoMax.get(i)*448f)/100) + margenIzq,(int) convertDpToPixel(16f));
                    margenIzq += (int) convertDpToPixel((afiladoMax.get(i)*448f)/100);
                    canvas.drawRect(rect, paintList.get(i));
                }

                ivAfiladoMax.setImageBitmap(bmp);
            }
        }else{
            lblAfilado.setVisibility(View.GONE);
            ivAfilado.setVisibility(View.GONE);
            ivAfiladoMax.setVisibility(View.GONE);
        }
    }

    private void setMejoras(Weapon weapon){
        List<Weapon> mejorasAMostrar = new LinkedList<>();

        Log.d(LOGTAG, "Cogiendo mejora de " + weapon.getID());
        for(int id : weapon.getMejoras()){
            Weapon weaponAdd = App.db.getWeapon(weapon.getTipo(), id);
            weaponAdd.setTipo(weapon.getTipo());
            mejorasAMostrar.add(weaponAdd);
        }

        Log.d(LOGTAG, "Mejoras de " + weapon.getID() + ": " + weapon.getMejoras());
        for(Weapon weapons : mejorasAMostrar){
            Log.d(LOGTAG, "Mejoras de son: " + mejorasAMostrar.toString());
        }
        rvMejoras = findViewById(R.id.rvMejoras);
        rvMejoras.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.mejorasAdapter = new WeaponLittleAdapter(this, mejorasAMostrar);
        this.mejorasAdapter.setClickListener(this);
        rvMejoras.setAdapter(this.mejorasAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent subArbol = new Intent(WeaponActivity.this, WeaponActivity.class);
        Weapon clicked = mejorasAdapter.getItem(position);

        App.weaponDetail = App.db.getWeapon(clicked.getTipo(), this.mejorasAdapter.getItem(position).getID());
        App.weaponDetail.setTipo(clicked.getTipo());
        subArbol.putExtra(App.WEAPON, clicked.getID());

        WeaponActivity.this.startActivity( subArbol );
    }

    public float convertDpToPixel(float dp){
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float DENSITY_DPI = metrics.densityDpi;
        float DENSITY_DEFAULT =  DisplayMetrics.DENSITY_DEFAULT;
        return dp * (DENSITY_DPI / DENSITY_DEFAULT);
    }

    public void setPaint(){
        Paint paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladorojo));
        paintList.add(0, paintL);
        paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladonaranja));
        paintList.add(1, paintL);
        paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladoamarillo));
        paintList.add(2, paintL);
        paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladoverde));
        paintList.add(3, paintL);
        paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladoazul));
        paintList.add(4, paintL);
        paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladoblanco));
        paintList.add(5, paintL);
        paintL = new Paint();
        paintL.setStyle(Paint.Style.FILL);
        paintL.setColor(ContextCompat.getColor(this, R.color.afiladonegro));
        paintList.add(6, paintL);
    }
}

package com.es.google.mhwvisualizer.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.es.google.mhwvisualizer.Core.Gear.Weapon;
import com.es.google.mhwvisualizer.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WeaponsActivity extends AppCompatActivity implements WeaponAdapter.ItemClickListener {
    public String LOGTAG = "WEAPONS_ACTIVITY";
    public WeaponAdapter armasAdapter;
    public List<Weapon> armas;
    public static boolean iconos = true;
    public String tipoArma;
    private Parcelable recyclerViewState;
    private RecyclerView rvWeapons;
    private int modoOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_weapons );

        this.modoOrden = 0;
        Intent datosEnviados = this.getIntent();
        tipoArma = datosEnviados.getExtras().getString(App.WEAPON);
        Log.d(LOGTAG, "Mostrando " + tipoArma);
        this.rellenarCon(tipoArma);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.getMenuInflater().inflate(R.menu.weapon_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean retorno = false;
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.opSwitchImg:
                this.iconos = !this.iconos;
                this.armasAdapter.switchImgs();
                break;

            case R.id.opOrdenar:
                ordenar();
                break;

            /*case R.id.opBuscar:
                buscador();
                break;*/

            case R.id.opSalir:
                this.finish();
                break;
        }

        return retorno;
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Guarda la posicion de la recycleview
        recyclerViewState = rvWeapons.getLayoutManager().onSaveInstanceState();
    }

    @Override
    protected void onResume(){
        super.onResume();

        //Carga la posicion de la recycleview
        rvWeapons.getLayoutManager().onRestoreInstanceState(recyclerViewState);

    }

    public void rellenarCon(String tipoArma){
        Log.d(LOGTAG, "Recuperando " + tipoArma + " de la BBDD");
        Cursor cursor = App.db.getWeapons(tipoArma);
            Log.d(LOGTAG, Arrays.asList(cursor.getColumnNames()).toString());
        List<Weapon> armas = new LinkedList<>();
        List<Integer> armasYaMostradas = new LinkedList<>();
        Weapon weapon;

        try {
            while (cursor.moveToNext()) {
                weapon = new Weapon();
                weapon.setTipo(tipoArma);
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    switch (cursor.getColumnName(i)) {
                        case "ID":
                            Log.d(LOGTAG, "ID: " + cursor.getInt(i));
                            weapon.setID(cursor.getInt(i));
                            break;
                        case "nombre":
                            Log.d(LOGTAG, "Nombre: " + cursor.getString(i));
                            weapon.setNombre(cursor.getString(i));
                            break;
                        case "rareza":
                            Log.d(LOGTAG, "Rareza: " + cursor.getInt(i));
                            weapon.setRareza(cursor.getInt(i));
                            break;
                        case "icon_enlace":
                            Log.d(LOGTAG, "Icono: " + cursor.getString(i));
                            weapon.setIcon(cursor.getString(i));
                            break;
                        case "img_enlace":
                            Log.d(LOGTAG, "Img: " + cursor.getString(i));
                            weapon.setImagen(cursor.getString(i));
                            break;
                        default:
                            break;
                    }
                    Log.d(LOGTAG, cursor.getColumnName(i) + " -> " + cursor.getString(i));
                }
                if (!armasYaMostradas.contains(weapon.getID())) {
                    armas.add(weapon);
                    armasYaMostradas.add(weapon.getID());
                }
            }
        }catch(Exception e){
            Log.e(LOGTAG, e.getMessage());
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }
        this.armas = armas;
        this.creaListaArmas();
    }



    public void creaListaArmas(){
        rvWeapons = findViewById(R.id.rvWeapons);
        rvWeapons.removeAllViews();
        rvWeapons.setLayoutManager(new LinearLayoutManager(this));
        this.armasAdapter = new WeaponAdapter(this.getApplicationContext(), this.armas, this.iconos, this.modoOrden);
        this.armasAdapter.setClickListener(this);
        rvWeapons.setAdapter(this.armasAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent subArbol = new Intent(WeaponsActivity.this, WeaponActivity.class);
        Weapon clicked = this.armasAdapter.getItem(position);

        Log.d(LOGTAG, "Seleccionada: " + clicked.getNombre() + " (" + clicked.getID() + ")");
        App.weaponDetail = App.db.getWeapon(clicked.getTipo(), clicked.getID());
        App.weaponDetail.setTipo(clicked.getTipo());
        subArbol.putExtra(App.WEAPON, clicked.getID());

        WeaponsActivity.this.startActivity( subArbol );
    }

    public void ordenar(){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        final CharSequence[] opcionesOrden = {getString(R.string.ordenDefault), getString(R.string.ordenAlfabetico), getString(R.string.ordenRareza)};

        dlg.setTitle(R.string.ordenar);
        dlg.setSingleChoiceItems(opcionesOrden, modoOrden, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                modoOrden = which;
                creaListaArmas();
                dialog.cancel();
            }
        });

        dlg.create().show();
    }

    public void buscador(){
        /**Actividad nueva con opciones para buscar
         *      Deberia cambiar si es un BOW/INSECTGLAIVE/HEAVYBOWGUN/LIGHTBOWGUN/DUALBLADES/HUNTINGHORN(AUNQUEYA VES TU)
         * Debería poder buscarse por:
         *      Danho
         *      Defensa
         *      Rareza
         *      Afilado? (simple por color)
         *      Sello Ancianos
         *      Afinidad
         *      Elemento, cantidad, oculto
         *      Huecos
         *          Bonus Insecto (INSECTGLAIVE)
         *          Desvio (HEAVY/LIGHT BOWGUN)
         *          Mun. Especial (HEAVY BOWGUN)
         *          Proyectil (GUNLANCE)
         *          Revestimientos (BOW)
         *          Notas (HUNTINGHORN)**/

    }
}

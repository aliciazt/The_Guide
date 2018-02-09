package com.example.dell.mymenunavegacion;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.Recreativos.RecreativosDetalle;
import com.example.dell.mymenunavegacion.Galeria.GaleriaDetalle;
import com.example.dell.mymenunavegacion.Recreativos.RecreativosFragment;
import com.example.dell.mymenunavegacion.Galeria.GaleriaFragment;
import com.example.dell.mymenunavegacion.Recreativos.RecreativosDetalleActivity;
import com.example.dell.mymenunavegacion.Galeria.GaleriaDetalleActivity;
import com.example.dell.mymenunavegacion.leyendas.LeyendasDetalle;
import com.example.dell.mymenunavegacion.leyendas.LeyendasDetalleActivity;
import com.example.dell.mymenunavegacion.leyendas.LeyendasFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IComunicaFragments {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_veracruz);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Aqui se muestra el inicio
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Contenedor, new InicioFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_inicio) {
            // aquie se muestra el inicio
            fragmentManager.beginTransaction().replace(R.id.Contenedor, new InicioFragment()).commit();
        } else if (id == R.id.nav_maps) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_leyendas) {
            Fragment Fragment = new LeyendasFragment();
            fragmentManager.beginTransaction().replace(R.id.Contenedor,Fragment).commit();
        } else if (id == R.id.nav_museos) {
            Fragment Fragment = new MuseosFragment();
            fragmentManager.beginTransaction().replace(R.id.Contenedor, Fragment).commit();
        } else if (id == R.id.nav_recreativos) {
            Fragment Fragment = new RecreativosFragment();
            fragmentManager.beginTransaction().replace(R.id.Contenedor, Fragment).commit();

        } else if (id == R.id.nav_galery) {
            Fragment Fragment = new GaleriaFragment();
            fragmentManager.beginTransaction().replace(R.id.Contenedor, Fragment).commit();

        } else if (id == R.id.Paginas) {
            Toast.makeText(this,"informacion e imagenes extraidas desde aguapasada.wordpress",Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.Librerias){

            Toast.makeText(this,"LIbreria de justificacion de texto de twiceyuan",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void enviarMuseo(String nombre, String imagen, String tipo, String direccion,
                            String historia, String act, String costo, String tel) {
        View v = findViewById(R.id.General_container); // validar container 2 en la vista original

        if (v == null) {
            Intent intent = new Intent(this, MuseosDetalleActivity.class);

            intent.putExtra(MuseosDetalle.ICON_KEY, imagen); //
            intent.putExtra(MuseosDetalle.TEXT_KEY, nombre);
            intent.putExtra(MuseosDetalle.DIR_KEY, direccion); //
            intent.putExtra(MuseosDetalle.TIPO_KEY,tipo );
            intent.putExtra(MuseosDetalle.TEL_KEY, tel); //
            intent.putExtra(MuseosDetalle.COST_KEY, costo);
            intent.putExtra(MuseosDetalle.HIS_KEY, historia); //
            intent.putExtra(MuseosDetalle.ACT_KEY, act);

            startActivity(intent);

        } else {

            Bundle bundle = new Bundle ();

            bundle.putString(MuseosDetalle.ICON_KEY, imagen); //
            bundle.putString(MuseosDetalle.TEXT_KEY, nombre);
            bundle.putString(MuseosDetalle.DIR_KEY, direccion); //
            bundle.putString(MuseosDetalle.TIPO_KEY,tipo );
            bundle.putString(MuseosDetalle.TEL_KEY, tel); //
            bundle.putString(MuseosDetalle.COST_KEY, costo);
            bundle.putString(MuseosDetalle.HIS_KEY, historia); //
            bundle.putString(MuseosDetalle.ACT_KEY, act);

            MuseosDetalle detailsFragment = MuseosDetalle.newInstance(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.General_container, detailsFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();

        }
    }

     @Override
     public void enviarRecreativo(String nombre, String imagen, String tipo, String direccion, String descripcion) {


         View v = findViewById(R.id.General_container); // validar container 2 en la vista original

         if (v == null) {
             Intent intent = new Intent(this, RecreativosDetalleActivity.class);

             intent.putExtra(RecreativosDetalle.ICON_KEY, imagen); //
             intent.putExtra(RecreativosDetalle.TEXT_KEY, nombre);
             intent.putExtra(RecreativosDetalle.DIR_KEY, direccion); //
             intent.putExtra(RecreativosDetalle.TIPO_KEY,tipo );
             intent.putExtra(RecreativosDetalle.DESCR_KEY, descripcion); //

             if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                 getWindow().setExitTransition(new Slide());
                 startActivity(intent,
                         ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
             }
             else {
                 startActivity(intent);
             }

         } else {

             Bundle bundle = new Bundle ();

             bundle.putString(RecreativosDetalle.ICON_KEY, imagen); //
             bundle.putString(RecreativosDetalle.TEXT_KEY, nombre);
             bundle.putString(RecreativosDetalle.DIR_KEY, direccion); //
             bundle.putString(RecreativosDetalle.TIPO_KEY,tipo );
             bundle.putString(RecreativosDetalle.DESCR_KEY, descripcion); //


             RecreativosDetalle detailsFragment = RecreativosDetalle.newInstance(bundle);
             FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

             transaction.replace(R.id.General_container, detailsFragment);
             transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
             transaction.commit();

         }

     }

    @Override
    public void enviarGaleria(String Nombre, String foto, String anio, String descripcion) {

        View v = findViewById(R.id.General_container); // validar container 2 en la vista original

        if (v == null) {
            Intent intent = new Intent(this, GaleriaDetalleActivity.class);

            intent.putExtra(GaleriaDetalle.ICON_KEY, foto); //
            intent.putExtra(GaleriaDetalle.TEXT_KEY, Nombre);
            intent.putExtra(GaleriaDetalle.ANIO_KEY, anio);
            intent.putExtra(GaleriaDetalle.DESCR_KEY, descripcion); //

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                getWindow().setExitTransition(new Explode());
                startActivity(intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

            }
            else {
                startActivity(intent);
            }


        } else {

            Bundle bundle = new Bundle ();

            bundle.putString(GaleriaDetalle.ICON_KEY, foto); //
            bundle.putString(GaleriaDetalle.TEXT_KEY, Nombre);
            bundle.putString(GaleriaDetalle.ANIO_KEY, anio );
            bundle.putString(GaleriaDetalle.DESCR_KEY, descripcion); //


            GaleriaDetalle detailsFragment = GaleriaDetalle.newInstance(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.General_container, detailsFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();

        }

    }

    @Override
    public void enviarLeyendas(String Nombre, String imagen, String tipo, String direccion, String url_leyenda) {
        View v = findViewById(R.id.General_container); // validar container 2 en la vista original

        if (v == null) {
            Intent intent = new Intent(this, LeyendasDetalleActivity.class);

            intent.putExtra(LeyendasDetalle.ICON_KEY, imagen); //
            intent.putExtra(LeyendasDetalle.TEXT_KEY, Nombre);
            intent.putExtra(LeyendasDetalle.DIR_KEY, direccion); //
            intent.putExtra(LeyendasDetalle.TIPO_KEY,tipo );
            intent.putExtra(LeyendasDetalle.CONT_KEY, url_leyenda); //

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                getWindow().setExitTransition(new Fade().setDuration(1000));
                startActivity(intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
            }
            else {
                startActivity(intent);
            }

        } else {

            Bundle bundle = new Bundle ();

            bundle.putString(LeyendasDetalle.ICON_KEY, imagen); //
            bundle.putString(LeyendasDetalle.TEXT_KEY, Nombre);
            bundle.putString(LeyendasDetalle.DIR_KEY, direccion); //
            bundle.putString(LeyendasDetalle.TIPO_KEY,tipo );
            bundle.putString(LeyendasDetalle.CONT_KEY, url_leyenda); //


            LeyendasDetalle detailsFragment = LeyendasDetalle.newInstance(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.General_container, detailsFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();

        }

    }

}


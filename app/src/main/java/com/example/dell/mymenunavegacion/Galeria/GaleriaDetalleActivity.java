package com.example.dell.mymenunavegacion.Galeria;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.Recreativos.RecreativosDetalle;


public class GaleriaDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_detalle);

        Bundle extras = getIntent().getExtras ();

        GaleriaDetalle detailsFragment = extras != null ?  GaleriaDetalle.newInstance (extras) :  GaleriaDetalle.newInstance (null);

         getSupportFragmentManager()
                .beginTransaction()
                 .replace(R.id.General_container,detailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

    }
}
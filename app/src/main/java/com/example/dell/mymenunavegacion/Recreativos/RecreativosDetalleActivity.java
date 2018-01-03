package com.example.dell.mymenunavegacion.Recreativos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.mymenunavegacion.MuseosDetalle;
import com.example.dell.mymenunavegacion.R;


public class RecreativosDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_detalle);

        Bundle extras = getIntent().getExtras ();

        RecreativosDetalle detailsFragment = extras != null ?  RecreativosDetalle.newInstance (extras) :  RecreativosDetalle.newInstance (null);

         getSupportFragmentManager()
                .beginTransaction()
                 .replace(R.id.General_container,detailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

    }
}
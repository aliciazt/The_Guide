package com.example.dell.mymenunavegacion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;



public class MuseosDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museos_detalle);

        Bundle extras = getIntent().getExtras ();

        MuseosDetalle detailsFragment = extras != null ? MuseosDetalle.newInstance (extras) : MuseosDetalle.newInstance (null);

         getSupportFragmentManager()
                .beginTransaction()
                 .replace(R.id.container2,detailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

    }
}
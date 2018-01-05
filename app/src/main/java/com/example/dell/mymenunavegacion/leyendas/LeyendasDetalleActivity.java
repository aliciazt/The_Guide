package com.example.dell.mymenunavegacion.leyendas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.mymenunavegacion.R;

/**
 * Created by Alicia Zarate on 4/1/2018.
 */

public class LeyendasDetalleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_detalle);
        Bundle extras = getIntent().getExtras();
        LeyendasDetalle detailsFragment=extras != null ? LeyendasDetalle.newInstance(extras) : LeyendasDetalle.newInstance(null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.General_container,detailsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}

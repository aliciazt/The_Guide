package com.example.dell.mymenunavegacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dell on 04/01/2018.
 */

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }
}

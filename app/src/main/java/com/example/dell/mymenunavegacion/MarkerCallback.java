package com.example.dell.mymenunavegacion;

import android.util.Log;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by Alicia Zarate on 31/12/2017.
 */

public class MarkerCallback implements  com.squareup.picasso.Callback {
    private Marker marker;
    public MarkerCallback(Marker marker){
        this.marker=marker;
    }
    @Override
    public void onSuccess() {
        if(marker!=null && marker.isInfoWindowShown()){
           marker.hideInfoWindow();
            marker.showInfoWindow();
        }
    }

    @Override
    public void onError() {
       //Log.d("hey","markercalback-on error");
    }
}

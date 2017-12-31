package com.example.dell.mymenunavegacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.zip.Inflater;

/**
 * Created by Alicia Zarate on 30/12/2017.
 */

public class Custominfowindowadapter implements GoogleMap.InfoWindowAdapter {
    private   View v;
    private Context mcontext;

    public Custominfowindowadapter(Context context) {
        mcontext = context;
        v=LayoutInflater.from(context).inflate(R.layout.info_window,null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        redo_window(marker,v);
        return v;
    }
 private void redo_window(Marker marker, View v){
     String[] info= marker.getSnippet().split(",");
     String nombre= marker.getTitle();
     String url= info[2];
     TextView title = (TextView) v.findViewById(R.id.titulo_w);
     TextView coste = (TextView) v.findViewById(R.id.costo_w);
     TextView type = (TextView) v.findViewById(R.id.tipo_w);
     title.setText(nombre);
     coste.setText("costo:"+""+info[0]);
     type.setText("tipo de marcador:"+""+info[1]);


 }
    @Override
    public View getInfoContents(Marker marker) {
        redo_window(marker,v);
        return v;
    }
}

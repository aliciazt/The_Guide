package com.example.dell.mymenunavegacion;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.zip.Inflater;

import static com.example.dell.mymenunavegacion.R.id.imageView;

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
        String[] info= marker.getSnippet().split(",");
        String nombre= marker.getTitle();
        String url= info[2];
        ImageView imagen = (ImageView)v.findViewById(R.id.imagen_w);
        Picasso.with(mcontext).load(url).fit().centerCrop().into(imagen,new MarkerCallback(marker));
        TextView title = (TextView) v.findViewById(R.id.titulo_w);
        TextView coste = (TextView) v.findViewById(R.id.costo_w);
        TextView type = (TextView) v.findViewById(R.id.tipo_w);
        title.setText(nombre);
        coste.setText("costo:"+""+info[0]);
        type.setText("tipo de marcador:"+""+info[1]);
        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}


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
        String infosnip= marker.getSnippet();
        ImageView imagen = (ImageView)v.findViewById(R.id.imagen_w);
        TextView title = (TextView) v.findViewById(R.id.titulo_w);
        TextView coste = (TextView) v.findViewById(R.id.costo_w);
        TextView type = (TextView) v.findViewById(R.id.tipo_w);
            if(infosnip!= null){
                String[] info= infosnip.split(",");
                 String nombre= marker.getTitle();
                 String url= info[2];
                //imagen.setImageResource(R.drawable.logo_opt);
            Picasso.with(mcontext).load(url).resize(100,100).centerCrop().into(imagen,new MarkerCallback(marker));
                 title.setText(nombre);
                if(info[0] .equals("null")){coste.setText("");}
                else{
                coste.setText("costo:"+""+info[0]);}
                type.setText("tipo de marcador:"+"\n"+info[1]);
            }else{
        imagen.setImageResource(R.drawable.usuario);
        title.setText(marker.getTitle());
        type.setText("Aqui estas actualmente");
            coste.setText("");}

        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}


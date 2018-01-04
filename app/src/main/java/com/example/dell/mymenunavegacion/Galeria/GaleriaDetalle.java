package com.example.dell.mymenunavegacion.Galeria;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.mymenunavegacion.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dell on 02/01/2018.
 */

public class GaleriaDetalle extends Fragment {

    public static String ICON_KEY = "foto_G";
    public static String TEXT_KEY = "text_G";
    public static String ANIO_KEY = "anio_G";
    public static String DESCR_KEY = "descr_G";


    public static GaleriaDetalle newInstance(@Nullable Bundle extras) {
        GaleriaDetalle fragment = new GaleriaDetalle();

        if (extras != null) {
            fragment.setArguments(extras);
        }

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_galeria, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView textView = (TextView) getActivity().findViewById(R.id.NOMBRE_G);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.G_imagen_detalle);
        TextView textView2 = (TextView) getActivity().findViewById(R.id.ANIO_G);
        TextView textView3 = (TextView) getActivity().findViewById(R.id.DESCRIPCION_G);


        Bundle extras = getArguments();

        if (extras != null) {
            String foto = (String) extras.get(GaleriaDetalle.ICON_KEY);
            String text = (String) extras.get(GaleriaDetalle.TEXT_KEY);
            String anio = (String) extras.get(GaleriaDetalle.ANIO_KEY);
            String descripcion = (String) extras.get(GaleriaDetalle.DESCR_KEY);


            textView.setText(text); //Nombre
            Picasso.with(getContext()).load(foto).into(imageView); //aqui cargamos la imagen
            textView2.setText(anio);
            textView3.setText(descripcion);

        }
    }
}

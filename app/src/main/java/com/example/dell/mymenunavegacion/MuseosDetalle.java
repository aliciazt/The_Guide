package com.example.dell.mymenunavegacion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import com.example.dell.mymenunavegacion.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dell on 02/01/2018.
 */

public class MuseosDetalle  extends Fragment {

    public static String ICON_KEY = "foto";
    public static String TEXT_KEY = "text";
    public static String DIR_KEY = "dir";
    public static String TIPO_KEY = "tipo";
    public static String TEL_KEY = "tel";
    public static String COST_KEY = "costo";
    public static String HIS_KEY = "hist";
    public static String ACT_KEY = "act";

    public static MuseosDetalle newInstance(@Nullable Bundle extras) {
        MuseosDetalle fragment = new MuseosDetalle();

        if (extras != null) {
            fragment.setArguments(extras);
        }

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_museo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView textView = (TextView) getActivity().findViewById(R.id.NOMBRE_M);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imagen_detalle);
        TextView textView1 = (TextView) getActivity().findViewById(R.id.DIRECCION_M);
        TextView textView2 = (TextView) getActivity().findViewById(R.id.TIPO_M);
        TextView textView3 = (TextView) getActivity().findViewById(R.id.TELEFONO_M);
        TextView textView4 = (TextView) getActivity().findViewById(R.id.HIST_M);
        TextView textView5 = (TextView) getActivity().findViewById(R.id.ACTIVIDADES_M);
        TextView textView6 = (TextView) getActivity().findViewById(R.id.COSTO_M);


        Bundle extras = getArguments();

        if (extras != null) {
            String foto = (String) extras.get(MuseosDetalle.ICON_KEY);
            String text = (String) extras.get(MuseosDetalle.TEXT_KEY);
            String direccion = (String) extras.get(MuseosDetalle.DIR_KEY);
            String tipo = (String) extras.get(MuseosDetalle.TIPO_KEY);
            String telefono = (String) extras.get(MuseosDetalle.TEL_KEY);
            String costo = (String) extras.get(MuseosDetalle.COST_KEY);
            String historia = (String) extras.get(MuseosDetalle.HIS_KEY);
            String actividades = (String) extras.get(MuseosDetalle.ACT_KEY);




            textView.setText(text); //Nombre
            Picasso.with(getContext()).load(foto).into(imageView); //aqui cargamos la imagen
            textView1.setText(direccion);
            textView2.setText(tipo);
            textView3.setText(telefono);
            textView4.setText(historia);
            textView5.setText(actividades);
            textView6.setText(costo);





        }
    }
}

package com.example.dell.mymenunavegacion.Recreativos;

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

public class RecreativosDetalle extends Fragment {

    public static String ICON_KEY = "foto_R";
    public static String TEXT_KEY = "text_R";
    public static String DIR_KEY = "dir_R";
    public static String TIPO_KEY = "tipo_R";
    public static String DESCR_KEY = "descr";


    public static RecreativosDetalle newInstance(@Nullable Bundle extras) {
        RecreativosDetalle fragment = new RecreativosDetalle();

        if (extras != null) {
            fragment.setArguments(extras);
        }

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_recreativo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView textView = (TextView) getActivity().findViewById(R.id.R_NOMBRE);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.R_imagen_detalle);
        TextView textView1 = (TextView) getActivity().findViewById(R.id.R_DIRECCION);
        TextView textView2 = (TextView) getActivity().findViewById(R.id.R_TIPO);
        TextView textView3 = (TextView) getActivity().findViewById(R.id.R_DESCRIPCION);


        Bundle extras = getArguments();

        if (extras != null) {
            String foto = (String) extras.get(RecreativosDetalle.ICON_KEY);
            String text = (String) extras.get(RecreativosDetalle.TEXT_KEY);
            String direccion = (String) extras.get(RecreativosDetalle.DIR_KEY);
            String tipo = (String) extras.get(RecreativosDetalle.TIPO_KEY);
            String descripcion = (String) extras.get(RecreativosDetalle.DESCR_KEY);





            textView.setText(text); //Nombre
            Picasso.with(getContext()).load(foto).into(imageView); //aqui cargamos la imagen
            textView1.setText(direccion);
            textView2.setText(tipo);
            textView3.setText(descripcion);






        }
    }
}

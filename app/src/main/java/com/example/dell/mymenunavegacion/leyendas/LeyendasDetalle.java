package com.example.dell.mymenunavegacion.leyendas;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.mymenunavegacion.DownloadTextFile;
import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.TextJustification;
import com.squareup.picasso.Picasso;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

/**
 * Created by Alicia Zarate on 4/1/2018.
 */

public class LeyendasDetalle extends Fragment {
    public static String ICON_KEY = "foto_R";
    public static String TEXT_KEY = "text_R";
    public static String DIR_KEY = "dir_R";
    public static String TIPO_KEY = "tipo_R";
    public static String CONT_KEY = "conte";

    public static  LeyendasDetalle newInstance(@Nullable  Bundle extras){
        LeyendasDetalle fragment= new LeyendasDetalle();

        if(extras != null){
            fragment.setArguments(extras);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_leyendas,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getActivity().findViewById(R.id.L_NOMBRE);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.L_imagen_detalle);
        TextView textView1 = (TextView) getActivity().findViewById(R.id.L_DIRECCION);
        TextView textView2 = (TextView) getActivity().findViewById(R.id.L_TIPO);
       TextView textView3 = (TextView) getActivity().findViewById(R.id.L_CONTENIDO);
        Bundle extras = getArguments();
        if(extras !=null){
            String foto = (String) extras.get(LeyendasDetalle.ICON_KEY);
            String text = (String) extras.get(LeyendasDetalle.TEXT_KEY);
            String direccion = (String) extras.get(LeyendasDetalle.DIR_KEY);
            String tipo = (String) extras.get(LeyendasDetalle.TIPO_KEY);
            String contenido = (String) extras.get(LeyendasDetalle.CONT_KEY);

            textView.setText(text);
            Picasso.with(getContext()).load(foto).into(imageView);
            textView1.setText(direccion);
            textView2.setText(tipo);
            String uri = contenido;
            new DownloadTextFile(textView3).execute (uri);





        }
    }
}



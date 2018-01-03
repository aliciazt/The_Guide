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

        TextView textView = (TextView) getActivity().findViewById(R.id.descripcion);
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imagen_detalle);


        Bundle extras = getArguments();

        if (extras != null) {
            String foto = (String) extras.get(MuseosDetalle.ICON_KEY); // aqui cambie
            //int icon2 = (int) extras.get(DetailsFragment.ICON_KEY); // aqui compara con otro
            String text = (String) extras.get(MuseosDetalle.TEXT_KEY);

            //String[] res = getResources().getStringArray(R.array.de);
            //String[] img = getResources().getStringArray(R.array.images);

            textView.setText(text); //titulo
            Picasso.with(getContext()).load(foto).into(imageView);
            //imageView.setImageResource(foto); //imagen





        }
    }
}

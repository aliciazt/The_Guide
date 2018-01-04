package com.example.dell.mymenunavegacion.Galeria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.Recreativos.recreativos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dell on 30/12/2017.
 */

public class AdapterGaleria extends RecyclerView.Adapter<AdapterGaleria.ViewHolderGaleria> {

    ArrayList<galeria> listgaleria;
    Context context;
    IComunicaFragments listener;

    public AdapterGaleria(Context context, ArrayList<galeria> listgaleria, IComunicaFragments listener) {
        this.listgaleria = listgaleria;
        this.context=context;
        this.listener = listener;
    }

    public class ViewHolderGaleria extends RecyclerView.ViewHolder {


        ImageView imagen;

        public ViewHolderGaleria(View itemView) {
            super(itemView);


            imagen = (ImageView) itemView.findViewById(R.id.G_imagen);
        }


    }
    @Override
    public AdapterGaleria.ViewHolderGaleria onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fotos_list, null, false);
        return new ViewHolderGaleria(view);
    }

    @Override
    public void onBindViewHolder(AdapterGaleria.ViewHolderGaleria holder, final int position) {



        Picasso.with(this.context).load(listgaleria.get(position).getImagen_url()).resize(100, 100).centerCrop().error(R.drawable.logo_opt).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nombre = listgaleria.get(position).getNombre();
                String foto = listgaleria.get(position).getImagen_url();
                String anio = listgaleria.get(position).getAnio();
                String descripcion = listgaleria.get(position).getDescripcion();


                listener.enviarGaleria(Nombre, foto, anio, descripcion);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listgaleria.size();
    }
}

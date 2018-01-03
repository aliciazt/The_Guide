package com.example.dell.mymenunavegacion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Dell on 30/12/2017.
 */

public class AdapterMuseos extends RecyclerView.Adapter<AdapterMuseos.ViewHolderMuseos> {

    ArrayList<museos> listmuseos;
    Context context;
    IComunicaFragments listener;

    public AdapterMuseos(Context context, ArrayList<museos> listmuseos,  IComunicaFragments listener) {
        this.listmuseos = listmuseos;
        this.context=context;
        this.listener = listener;
    }

    public class ViewHolderMuseos extends RecyclerView.ViewHolder {

        TextView nombre, tipo, direccion, telefono;
        ImageView imagen;

        public ViewHolderMuseos(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            tipo = (TextView) itemView.findViewById(R.id.tipo);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
            telefono = (TextView) itemView.findViewById(R.id.telefono);
            imagen = (ImageView) itemView.findViewById(R.id.id_imagen);
        }


    }
    @Override
    public AdapterMuseos.ViewHolderMuseos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.museos_list, null, false);
        return new ViewHolderMuseos(view);
    }

    @Override
    public void onBindViewHolder(AdapterMuseos.ViewHolderMuseos holder,final int position) {


        holder.nombre.setText(listmuseos.get(position).getNombre());
        holder.tipo.setText(listmuseos.get(position).getTipo());
        holder.direccion.setText(listmuseos.get(position).getDireccion());
        holder.telefono.setText(listmuseos.get(position).getTelefono());
        Picasso.with(this.context).load(listmuseos.get(position).getImagen_url()).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nombre = listmuseos.get(position).getNombre();
                String foto = listmuseos.get(position).getImagen_url();

                listener.enviarMuseo(Nombre, foto);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listmuseos.size();
    }
}

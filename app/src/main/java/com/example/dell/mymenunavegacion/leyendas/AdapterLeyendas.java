package com.example.dell.mymenunavegacion.leyendas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Alicia Zarate on 4/1/2018.
 */

public class AdapterLeyendas extends RecyclerView.Adapter<AdapterLeyendas.ViewHolderLeyendas> {
    ArrayList<leyendas> listleyendas;
    Context context;
    IComunicaFragments listener;

    public AdapterLeyendas( Context context,ArrayList<leyendas> listleyendas, IComunicaFragments listener) {
        this.listleyendas = listleyendas;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolderLeyendas extends RecyclerView.ViewHolder {
        TextView nombre, tipo, direccion;
        ImageView imagen;
        public ViewHolderLeyendas(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.l_nombre);
            tipo = (TextView) itemView.findViewById(R.id.l_tipo);
            direccion = (TextView) itemView.findViewById(R.id.l_direccion);
            imagen = (ImageView) itemView.findViewById(R.id.l_imagen);
        }
    }
    @Override
    public AdapterLeyendas.ViewHolderLeyendas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leyendas_list, null, false);
        return new ViewHolderLeyendas(view);
    }

    @Override
    public void onBindViewHolder(AdapterLeyendas.ViewHolderLeyendas holder, final int position) {
        holder.nombre.setText(listleyendas.get(position).getL_nombre());
        holder.direccion.setText(listleyendas.get(position).getL_direccion());
        holder.tipo.setText(listleyendas.get(position).getL_tipo());
        Picasso.with(this.context).load(listleyendas.get(position).getL_imagen_url()).resize(100, 100).centerCrop().error(R.drawable.logo_opt).into(holder.imagen);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = listleyendas.get(position).getL_nombre();
                String foto = listleyendas.get(position).getL_imagen_url();
                String tipo = listleyendas.get(position).getL_tipo();
                String direccion= listleyendas.get(position).getL_direccion();
                String contenido = listleyendas.get(position).getUrl_leyenda();

                listener.enviarLeyendas(Nombre,foto,tipo,direccion,contenido);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listleyendas.size();
    }


}

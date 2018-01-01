package com.example.dell.mymenunavegacion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 30/12/2017.
 */

public class AdapterMuseos extends RecyclerView.Adapter<AdapterMuseos.ViewHolderMuseos> {

    ArrayList<museos> listmuseos;

    public AdapterMuseos(ArrayList<museos> listmuseos) {
        this.listmuseos = listmuseos;
    }

    public class ViewHolderMuseos extends RecyclerView.ViewHolder {

        TextView nombre, tipo, direccion, telefono;

        public ViewHolderMuseos(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            tipo = (TextView) itemView.findViewById(R.id.tipo);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
            telefono = (TextView) itemView.findViewById(R.id.telefono);
        }


    }
    @Override
    public AdapterMuseos.ViewHolderMuseos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.museos_list, null, false);
        return new ViewHolderMuseos(view);
    }

    @Override
    public void onBindViewHolder(AdapterMuseos.ViewHolderMuseos holder, int position) {


        holder.nombre.setText(listmuseos.get(position).getNombre());
        holder.tipo.setText(listmuseos.get(position).getTipo());
        holder.direccion.setText(listmuseos.get(position).getDireccion());
        holder.telefono.setText(listmuseos.get(position).getTelefono());


    }

    @Override
    public int getItemCount() {
        return listmuseos.size();
    }
}

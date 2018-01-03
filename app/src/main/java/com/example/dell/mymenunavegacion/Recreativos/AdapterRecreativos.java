package com.example.dell.mymenunavegacion.Recreativos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.museos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dell on 30/12/2017.
 */

public class AdapterRecreativos extends RecyclerView.Adapter<AdapterRecreativos.ViewHolderRecreativos> {

    ArrayList<recreativos> listrecretivos;
    Context context;
    IComunicaFragments listener;

    public AdapterRecreativos(Context context, ArrayList<recreativos> listrecreativos, IComunicaFragments listener) {
        this.listrecretivos = listrecreativos;
        this.context=context;
        this.listener = listener;
    }

    public class ViewHolderRecreativos extends RecyclerView.ViewHolder {

        TextView nombre, tipo, direccion;
        ImageView imagen;

        public ViewHolderRecreativos(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.R_nombre);
            tipo = (TextView) itemView.findViewById(R.id.R_tipo);
            direccion = (TextView) itemView.findViewById(R.id.R_direccion);
            imagen = (ImageView) itemView.findViewById(R.id.R_imagen);
        }


    }
    @Override
    public AdapterRecreativos.ViewHolderRecreativos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recreativos_list, null, false);
        return new ViewHolderRecreativos(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecreativos.ViewHolderRecreativos holder, final int position) {


        holder.nombre.setText(listrecretivos.get(position).getNombre());
        holder.tipo.setText(listrecretivos.get(position).getTipo());
        holder.direccion.setText(listrecretivos.get(position).getDireccion());
        Picasso.with(this.context).load(listrecretivos.get(position).getImagen_url()).resize(100, 100).centerCrop().error(R.drawable.logo_opt).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nombre = listrecretivos.get(position).getNombre();
                String foto = listrecretivos.get(position).getImagen_url();
                String tipo = listrecretivos.get(position).getTipo();
                String direccion = listrecretivos.get(position).getDireccion();
                String descripcion = listrecretivos.get(position).getDescripcion();


                listener.enviarRecreativo(Nombre, foto, tipo, direccion, descripcion);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listrecretivos.size();
    }
}

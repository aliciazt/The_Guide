package com.example.dell.mymenunavegacion.Galeria;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.Recreativos.AdapterRecreativos;
import com.example.dell.mymenunavegacion.Recreativos.recreativos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class GaleriaFragment extends Fragment {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    RecyclerView recyclerGalery;
    IComunicaFragments listener;

    public void onAttach(Context context) { //onAttack solo en el fragment
        super.onAttach(context);

        if (context instanceof IComunicaFragments) {
            listener = (IComunicaFragments) context;
        } else {
            throw new ClassCastException("Error");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_galeria, container, false);
        recyclerGalery = (RecyclerView) v.findViewById(R.id.recycler_galeria);

        //recyclerGalery.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerGalery.setLayoutManager(new GridLayoutManager(getContext(),2));
        retrievedata();

        return v;
    }

    private void  retrievedata(){
        databaseReference.child("GALERIA").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<galeria> listgalery = new ArrayList<galeria>();
                for(DataSnapshot entry: dataSnapshot.getChildren()){
                    galeria place = new galeria();



                    DataSnapshot foo=entry.child("NOMBRE");
                    place.nombre= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("DESCRIPCION");
                    place.descripcion= foo.getValue() != null ? foo.getValue().toString(): "";


                    foo=entry.child("IMAGEN_URL");
                    place.imagen_url=foo.getValue() != null ? foo.getValue().toString():"";

                    foo=entry.child("AÑO");
                    place.anio=foo.getValue() != null ? foo.getValue().toString():"";



                    listgalery.add(place);

                }
                AdapterGaleria adapter = new AdapterGaleria(getContext(), listgalery, listener);
                recyclerGalery.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

}

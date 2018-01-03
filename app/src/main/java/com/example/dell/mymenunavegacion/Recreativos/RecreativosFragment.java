package com.example.dell.mymenunavegacion.Recreativos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.mymenunavegacion.AdapterMuseos;
import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.R;
import com.example.dell.mymenunavegacion.museos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RecreativosFragment extends Fragment {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    RecyclerView recyclerRecreativos;
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
        View v = inflater.inflate(R.layout.fragment_recreativos, container, false);
        recyclerRecreativos = (RecyclerView) v.findViewById(R.id.recycler_recreativos);

        recyclerRecreativos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        retrievedata();

        return v;
    }

    private void  retrievedata(){
        databaseReference.child("RECREATIVOS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<recreativos> listrecreativos = new ArrayList<recreativos>();
                for(DataSnapshot entry: dataSnapshot.getChildren()){
                    recreativos place = new recreativos();



                    DataSnapshot foo=entry.child("NOMBRE");
                    place.nombre= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("DESCRIPCION");
                    place.descripcion= foo.getValue() != null ? foo.getValue().toString(): "";


                    foo=entry.child("DIRECCION");
                    place.direccion= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("IMAGEN_URL");
                    place.imagen_url=foo.getValue() != null ? foo.getValue().toString():"";

                    foo=entry.child("TIPO");
                    place.tipo=foo.getValue() != null ? foo.getValue().toString():"";



                    listrecreativos.add(place);

                }
                AdapterRecreativos adapter = new AdapterRecreativos(getContext(), listrecreativos, listener);
                recyclerRecreativos.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

}

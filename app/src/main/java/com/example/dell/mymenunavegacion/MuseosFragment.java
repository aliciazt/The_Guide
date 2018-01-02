package com.example.dell.mymenunavegacion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MuseosFragment extends Fragment {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    //ArrayList<museos> listmuseos;
    RecyclerView recyclerMuseos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_museos, container, false);
        recyclerMuseos = (RecyclerView) v.findViewById(R.id.recycler_museos);
        //listmuseos = new ArrayList<museos>();
        recyclerMuseos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        // LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        // llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerMuseos.setLayoutManager(llm);
        retrievedata();
       // llenar();
        //AdapterMuseos adapter = new AdapterMuseos(listmuseos);
        //recyclerMuseos.setAdapter(adapter);
        return v;
    }

    private void  retrievedata(){
        databaseReference.child("MUSEOS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<museos> listmuseos = new ArrayList<museos>();
                for(DataSnapshot entry: dataSnapshot.getChildren()){
                    museos place = new museos();

                    DataSnapshot foo=entry.child("NOMBRE");
                    place.nombre= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("DIRECCION");
                    place.direccion= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("TELEFONO");
                    place.telefono= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("TIPO");
                    place.tipo=foo.getValue() != null ? foo.getValue().toString():"";

                    foo=entry.child("IMAGEN_URL");
                    place.imagen_url=foo.getValue() != null ? foo.getValue().toString():"";

                    listmuseos.add(place);

                }
                AdapterMuseos adapter = new AdapterMuseos(getContext(), listmuseos);
                recyclerMuseos.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
/*
    private  void llenar(){
        listmuseos.add(new museos("La nube","la escoba", "Perro", "2222222222"));
        listmuseos.add(new museos("Papelera", "caraver", "duna","2222222222"));
        listmuseos.add(new museos("Museo Naval", "caraver", "algo","2222222222"));
    }*/
}

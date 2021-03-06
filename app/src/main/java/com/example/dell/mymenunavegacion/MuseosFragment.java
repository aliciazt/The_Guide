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

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
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

                    foo=entry.child("ACTIVIDADES");
                    place.actividades= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("COSTO");
                    place.costo= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("DIRECCION");
                    place.direccion= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("HISTORIA");
                    place.Historia= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("IMAGEN_URL");
                    place.imagen_url=foo.getValue() != null ? foo.getValue().toString():"";

                    foo=entry.child("TELEFONO");
                    place.telefono= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("TIPO");
                    place.tipo=foo.getValue() != null ? foo.getValue().toString():"";



                    listmuseos.add(place);

                }
                AdapterMuseos adapter = new AdapterMuseos(getContext(), listmuseos, listener);
                recyclerMuseos.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

}

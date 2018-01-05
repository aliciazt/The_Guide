package com.example.dell.mymenunavegacion.leyendas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.mymenunavegacion.Interfaces.IComunicaFragments;
import com.example.dell.mymenunavegacion.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Alicia Zarate on 4/1/2018.
 */

public class LeyendasFragment  extends Fragment{
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    RecyclerView recyclerleyendas;
    IComunicaFragments listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IComunicaFragments){
            listener=(IComunicaFragments)context;
        }else{
            throw new ClassCastException("Error");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_leyendas,container,false);
        recyclerleyendas = (RecyclerView)v.findViewById(R.id.recycler_leyendas);

        recyclerleyendas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        retrievedata();
        return v;
    }

    private void retrievedata(){
        databaseReference.child("LEYENDAS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<leyendas> listleyendas = new ArrayList<leyendas>();
                for(DataSnapshot entry: dataSnapshot.getChildren()){
                    leyendas place = new leyendas ();

                    DataSnapshot foo=entry.child("NOMBRE");
                    place.l_nombre= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("URL_LEYE");
                    place.url_leyenda= foo.getValue() != null ? foo.getValue().toString(): "";


                    foo=entry.child("DIRECCION");
                    place.l_direccion= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("IMAGEN_URL");
                    place.l_imagen_url=foo.getValue() != null ? foo.getValue().toString():"";

                    foo=entry.child("TIPO");
                    place.l_tipo=foo.getValue() != null ? foo.getValue().toString():"";

                    listleyendas.add(place);

                }
                AdapterLeyendas adapter = new AdapterLeyendas(getContext(),listleyendas,listener);
                recyclerleyendas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

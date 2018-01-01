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

import java.util.ArrayList;


public class MuseosFragment extends Fragment {

    ArrayList<museos> listmuseos;
    RecyclerView recyclerMuseos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_museos, container, false);
        recyclerMuseos = (RecyclerView) v.findViewById(R.id.recycler_museos);
        listmuseos = new ArrayList<>();
        recyclerMuseos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        // LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        // llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerMuseos.setLayoutManager(llm);

        llenar();
        AdapterMuseos adapter = new AdapterMuseos(listmuseos);
        recyclerMuseos.setAdapter(adapter);
        return v;
    }

    private  void llenar(){
        listmuseos.add(new museos("La nube","la escoba", "Perro", "2222222222"));
        listmuseos.add(new museos("Papelera", "caraver", "duna","2222222222"));
        listmuseos.add(new museos("Museo Naval", "caraver", "algo","2222222222"));
    }
}

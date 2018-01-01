package com.example.dell.mymenunavegacion;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{
    public static LocationManager locationmanager;
    public static Location location;
    public static LocationListener locationlistener;
    private GoogleMap mMap;
    private Marker marcador;
    double lat=0.0;
    double longi=0.0;
    private static final  int LOCATION_PERMISSION_REQUEST_CODE =1;
    private boolean mPermissionDenied = false; //est madre ni se ocupa
 //cosas para firebase
   private static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    //lista para guardar los museos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ArrayAdapter<CharSequence> adapter;
        Spinner spinner = (Spinner) findViewById(R.id.filtro);
        adapter=ArrayAdapter.createFromResource(this,R.array.hijos,android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(5);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:mMap.clear();
                        miubicacion();
                        retrievedata("MUSEOS");
                        break;
                    case 1:mMap.clear();
                            miubicacion();
                            retrievedata("HISTORICOS");
                        break;
                    case 2:mMap.clear();
                        miubicacion();
                        retrievedata("RECREATIVOS");
                        break;
                    case 3:mMap.clear();
                        miubicacion();
                        retrievedata("LEYENDAS");
                        break;
                    case 4: mMap.clear();
                        miubicacion();
                        retrievedata("MUSEOS");
                        retrievedata("HISTORICOS");
                        retrievedata("LEYENDAS");
                        retrievedata("RECREATIVOS");
                    case 5: mMap.clear();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapStyle(new MapStyleOptions(getResources().getString(R.string.style_json)));
        //lo primero que hacemos es checar los permisos
        LatLng giu = new LatLng(19.151801, -96.110851);
        CameraUpdate miubi = CameraUpdateFactory.newLatLngZoom(giu,13);
         mMap.animateCamera(miubi); //mueve la camara hasta la posicion inicial de la ciudad, vista general

        checarpermisos();


    }
    private void checarpermisos(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
        } else {
            int haslocationPermission = checkSelfPermission(permission.ACCESS_FINE_LOCATION);
            if (haslocationPermission!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
               Toast.makeText(this, "Requesting permissions", Toast.LENGTH_LONG).show();
            }else if (haslocationPermission== PackageManager.PERMISSION_GRANTED){
                //si los permisos ya estaban activados entonces buscamos la ubicacion;
                Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
                miubicacion();
            }
        }
        return;
    }
        //checamos el resultado del pop-up que mostramos al usuario
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(LOCATION_PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //si los acepto entonces buscamos la ubicacion
                Toast.makeText(this, "OK Permissions granted ! 🙂 " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                miubicacion();
            } else {
                //no los acepto entonces vamonos de regreso a la primera activity;
                Toast.makeText(this, "Permissions are not granted ! 🙁   " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
//perdoname diosito, pero lo copie de internet, ubica al usuario
    public void miubicacion(){

        locationmanager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        location =  locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarubicacion(location);
        locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,loclistener);
        locationlistener = loclistener;
    }
//variable que ocupamos aca arriba, tambien salio del vasto mundo del internet
LocationListener loclistener=new LocationListener() {
    //cada vez que cambia la ubicacion entonces se va a actualizar el marcador
    @Override
    public void onLocationChanged(Location location) {
        actualizarubicacion(location);
    }
//este codigo se agrego solito, ya ni me acuerdo pa´que es!
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { }

    @Override
    public void onProviderEnabled(String s) { }

    @Override
    public void onProviderDisabled(String s) { }
};
//esta esta mandando constantemente el cambio de las coordenadas del usuario
    private void actualizarubicacion(Location location){
        if(location!=null){
            lat=location.getLatitude();
            longi=location.getLongitude();
            agregarmarcador(lat,longi);
        }

    }
//este es el que finalmente agrega al marcador del usuario en el mapa y cada vez que se pide la actualizacion vuelve a poner el marker
    private LatLng agregarmarcador(double lat, double lng){
        LatLng coordenadas = new LatLng(lat,lng);
        if(marcador!=null) marcador.remove();
        marcador= mMap.addMarker( new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion"));
        CameraPosition camera = new CameraPosition.Builder()
                .target(coordenadas)
                .build();


        mMap.setMinZoomPreference(13);
        mMap.setMaxZoomPreference(19);
        return coordenadas;
    }

    //funcion para tomar los valores de la base de datos;
    private void  retrievedata(final String hijo){
        databaseReference.child(hijo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<markers_maps> marker_list = new ArrayList<markers_maps>();
                for(DataSnapshot entry: dataSnapshot.getChildren()){
                    markers_maps place = new markers_maps();

                    DataSnapshot foo=entry.child("NOMBRE");
                    place.nombre= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo =entry.child("LATITUD");
                    place.latitud = foo.getValue() != null ? Double.parseDouble(foo.getValue().toString()): 10;

                    foo=entry.child("LONGITUD");
                    place.longitud = foo.getValue() != null ? Double.parseDouble(foo.getValue().toString()) : 10 ;

                    foo=entry.child("IMAGEN_URL");
                    place.imagen_url= foo.getValue() != null ? foo.getValue().toString(): "";

                        if(hijo.equals("MUSEOS")){
                    foo=entry.child("COSTO");
                    place.costo= foo.getValue() != null ? foo.getValue().toString(): "";}

                    foo=entry.child("TIPO");
                    place.tipo=foo.getValue() != null ? foo.getValue().toString():"";

                    marker_list.add(place);

                }
                ponemoslosmarker(marker_list);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void ponemoslosmarker(ArrayList<markers_maps> hola){
    ///SE GUARDA INFORMACION EN EL SNIPET QUE SE USARA PARA PONERLA EN LA VENTANA DE INFORMACION
        LatLng coorde;
        for (int i =0; i<hola.size();i++){
            coorde= new LatLng(hola.get(i).latitud,hola.get(i).longitud);
            mMap.addMarker(new MarkerOptions().position(coorde).title(hola.get(i).nombre).snippet(hola.get(i).costo+","+hola.get(i).tipo+","+hola.get(i).imagen_url));
            mMap.setInfoWindowAdapter(new Custominfowindowadapter(MapsActivity.this));

        }
    }


}



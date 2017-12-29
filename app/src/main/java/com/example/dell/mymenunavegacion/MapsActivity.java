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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;


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
        Button filtro = (Button) findViewById(R.id.filter);
        filtro.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               // onCreateOptionsMenu();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //lo primero que hacemos es checar los permisos
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
    private void agregarmarcador(double lat, double lng){
        LatLng coordenadas = new LatLng(lat,lng);
        CameraUpdate miubi = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
        if(marcador!=null) marcador.remove();
        marcador= mMap.addMarker( new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion"));
        CameraPosition camera = new CameraPosition.Builder()
                .target(coordenadas)
                .build();
        mMap.animateCamera(miubi); //mueve la camara hasta la posicion del marcador de mi ubicacion.
        mMap.setMinZoomPreference(12);
        mMap.setMaxZoomPreference(19);
    }

    //funcion para tomar los valores de la base de datos;
    private void  retrievedata(){
        databaseReference.child("MUSEOS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<museos> muse = new ArrayList<museos>();
                for(DataSnapshot entry: dataSnapshot.getChildren()){
                    museos museo = new museos();

                    DataSnapshot foo=entry.child("NOMBRE");
                    museo.nombre= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo =entry.child("LATITUD");
                    museo.Latitud = foo.getValue() != null ? Double.parseDouble(foo.getValue().toString()): 10;

                    foo=entry.child("LONGITUD");
                    museo.Longitud = foo.getValue() != null ? Double.parseDouble(foo.getValue().toString()) : 10 ;

                    foo=entry.child("IMAGEN_URL ");
                    museo.imagen_url= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("COSTO");
                    museo.costo= foo.getValue() != null ? foo.getValue().toString(): "";

                    foo=entry.child("TIPO");
                    museo.tipo=foo.getValue() != null ? foo.getValue().toString():"";

                    muse.add(museo);

                }
                ponemoslosmarker(muse);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    private void ponemoslosmarker(ArrayList<museos> hola){

        LatLng coorde;
        for (int i =0; i<hola.size();i++){
            //Toast.makeText(this,""+hola.get(i).nombre,Toast.LENGTH_LONG).show();
            coorde= new LatLng(hola.get(i).Latitud,hola.get(i).Longitud);
            mMap.addMarker(new MarkerOptions().position(coorde).title(hola.get(i).nombre));
        }
    }
    //FUNCIONARA EL MENU?
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mapa,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_museos:
                retrievedata();
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}



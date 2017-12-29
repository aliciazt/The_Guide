package com.example.dell.mymenunavegacion;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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
    }

    //funcion para tomar los valores de la base de datos;
    private void  retrievedata(){
       // Toast.makeText(this, "entre a la funcion",Toast.LENGTH_LONG).show();
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

                    foo=entry.child("SITIO");
                    museo.url_sitio= foo.getValue() != null ? foo.getValue().toString(): "";

                    muse.add(museo);

                }
                ponemoslosmarker(muse);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        checarpermisos(); //hace todo lo de los permisos
        retrievedata();

    }
    private void ponemoslosmarker(ArrayList<museos> hola){

        LatLng coorde;
        for (int i =0; i<hola.size();i++){
        //Toast.makeText(this,""+hola.get(i).nombre,Toast.LENGTH_LONG).show();
            coorde= new LatLng(hola.get(i).Latitud,hola.get(i).Longitud);
            mMap.addMarker(new MarkerOptions().position(coorde).title(hola.get(i).nombre));
     }
    }


    private void checarpermisos(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();

        } else {

            int haslocationPermission = checkSelfPermission(permission.ACCESS_FINE_LOCATION);

            if (haslocationPermission!= PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[] {permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);

              //  Toast.makeText(this, "Requesting permissions", Toast.LENGTH_LONG).show();

            }else if (haslocationPermission== PackageManager.PERMISSION_GRANTED){

              //  Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
                miubicacion();

            }

        }

        return;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(LOCATION_PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "OK Permissions granted ! 🙂 " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                miubicacion();
            } else {
                Toast.makeText(this, "Permissions are not granted ! 🙁   " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

private void agregarmarcador(double lat, double lng){
    LatLng coordenadas = new LatLng(lat,lng);
    CameraUpdate miubi = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
    if(marcador!=null) marcador.remove();
    marcador= mMap.addMarker( new MarkerOptions()
    .position(coordenadas)
    .title("Mi posicion"));
    CameraPosition camera = new CameraPosition.Builder()
            .target(coordenadas)
            .zoom(19)           // max 21
            .bearing(0)         //rotacion de la camara
            .tilt(90)           //efecto 3d
            .build();
     mMap.animateCamera(miubi); //mueve la camara hasta la posicion del marcador de mi ubicacion.
  // mMap.setMinZoomPreference(15);
    mMap.setMaxZoomPreference(17);
}
private void actualizarubicacion(Location location){
    if(location!=null){
        lat=location.getLatitude();
        longi=location.getLongitude();
        agregarmarcador(lat,longi);
    }

}

LocationListener loclistener=new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
        actualizarubicacion(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
};

public void miubicacion(){

    locationmanager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
        return;
    }
    location =  locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    actualizarubicacion(location);
    locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,0,loclistener);
    locationlistener = loclistener;
}



}

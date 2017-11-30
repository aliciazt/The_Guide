package com.example.dell.mymenunavegacion;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static LocationManager locationmanager;
    public static Location location;
    public static LocationListener locationlistener;
    private GoogleMap mMap;
    private Marker marcador;
    double lat=0.0;
    double longi=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        miubicacion();
        /*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMinZoomPreference(15);
        mMap.setMaxZoomPreference(19);
        CameraPosition camera = new CameraPosition.Builder()
                .target(sydney)
                .zoom(19)           // max 21
                .bearing(0)         //rotacion de la camara
                .tilt(90)           //efecto 3d
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
      //  mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);*/
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
   mMap.setMinZoomPreference(15);
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

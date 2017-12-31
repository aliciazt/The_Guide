package com.example.dell.mymenunavegacion;

/**
 * Created by Alicia Zarate on 23/12/2017.
 */
/*clase  utilizable para el pedido de informaciona firebase sobre los museos*/

public class museos {
    public String imagen_url;
    public double Latitud;
    public double Longitud;
    public String costo;
    public String nombre;
    public String tipo;
    public String actividades;
    public String direccion;
    public String Historia;
    public String telefono;

    public museos(String imagen_url, double latitud, double longitud, String costo,
                  String nombre, String tipo, String actividades, String direccion,
                  String historia, String telefono) {
        this.imagen_url = imagen_url;
        Latitud = latitud;
        Longitud = longitud;
        this.costo = costo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.actividades = actividades;
        this.direccion = direccion;
        Historia = historia;
        this.telefono = telefono;
    }
    public museos(String nombre, String tipo,  String direccion, String telefono) {

        this.nombre = nombre;
        this.tipo = tipo;

        this.direccion = direccion;

        this.telefono = telefono;
    }
    public museos() {


    }



    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHistoria() {
        return Historia;
    }

    public void setHistoria(String historia) {
        Historia = historia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}



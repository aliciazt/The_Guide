package com.example.dell.mymenunavegacion.Galeria;

/**
 * Created by Dell on 02/01/2018.
 */

public class galeria {

    public String imagen_url;
    public String nombre;
    public String anio;
    public String descripcion;

    public galeria(String imagen_url, String nombre, String anio, String descripcion) {
        this.imagen_url = imagen_url;
        this.nombre = nombre;
        this.anio = anio;
        this.descripcion = descripcion;
    }

    public galeria() {
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

package com.example.dell.mymenunavegacion.Recreativos;

/**
 * Created by Dell on 02/01/2018.
 */

public class recreativos {

    public String imagen_url;
    public String nombre;
    public String tipo;
    public String descripcion;
    public String direccion;

    public recreativos(String imagen_url, String nombre, String tipo, String descripcion, String direccion) {
        this.imagen_url = imagen_url;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public recreativos() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

package com.example.dell.mymenunavegacion.leyendas;

/**
 * Created by Alicia Zarate on 4/1/2018.
 */

public class leyendas {
    public String l_imagen_url;
    public String l_nombre;
    public String l_tipo;
    public String url_leyenda;
    public String l_direccion;

    public leyendas(String l_imagen_url, String l_nombre, String l_tipo, String url_leyenda, String l_direccion) {
        this.l_imagen_url = l_imagen_url;
        this.l_nombre = l_nombre;
        this.l_tipo = l_tipo;
        this.url_leyenda = url_leyenda;
        this.l_direccion = l_direccion;
    }

    public leyendas() {
    }

    public void setL_imagen_url(String l_imagen_url) {
        this.l_imagen_url = l_imagen_url;
    }

    public void setL_nombre(String l_nombre) {
        this.l_nombre = l_nombre;
    }

    public void setL_tipo(String l_tipo) {
        this.l_tipo = l_tipo;
    }

    public void setUrl_leyenda(String url_leyenda) {
        this.url_leyenda = url_leyenda;
    }

    public void setL_direccion(String l_direccion) {
        this.l_direccion = l_direccion;
    }

    public String getL_imagen_url() {
        return l_imagen_url;
    }

    public String getL_nombre() {
        return l_nombre;
    }

    public String getL_tipo() {
        return l_tipo;
    }

    public String getUrl_leyenda() {
        return url_leyenda;
    }

    public String getL_direccion() {
        return l_direccion;
    }
}

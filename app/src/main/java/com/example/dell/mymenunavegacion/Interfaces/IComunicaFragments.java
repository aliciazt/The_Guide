package com.example.dell.mymenunavegacion.Interfaces;

import com.example.dell.mymenunavegacion.museos;

/**
 * Created by Dell on 02/01/2018.
 */

public interface IComunicaFragments {

   abstract public void enviarMuseo(String nombre, String imagen,
                                    String tipo, String direccion, String historia, String act, String costo, String tel);

   abstract public void enviarRecreativo(String nombre, String imagen,
                                    String tipo, String direccion, String descripcion);
}

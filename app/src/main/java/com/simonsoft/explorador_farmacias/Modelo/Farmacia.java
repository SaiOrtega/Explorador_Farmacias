package com.simonsoft.explorador_farmacias.Modelo;


import android.view.LayoutInflater;

import com.simonsoft.explorador_farmacias.ui.home.HomeFragment;

import java.io.Serializable;
import java.util.List;

public class Farmacia implements Serializable {

    private String nombre;
    private String direccion;

    private String horario;

    private  int imageId;

    public Farmacia(List<Farmacia> farmacias, HomeFragment homeFragment, LayoutInflater layoutInflater) {
    }

    public Farmacia(String nombre, String direccion, String horario, int imageId) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horario = horario;

        this.imageId = imageId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }





    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

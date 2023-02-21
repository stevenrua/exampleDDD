package com.example.storecare.storecar.domain;

import com.example.storecare.storecar.domain.generic.Entity;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TrabajdorID;

import java.util.List;

public class Trabajador extends Entity<TrabajdorID> {

    private final List<String> listaTareas = null;
    private final Nombre nombre;


    public Trabajador(TrabajdorID trabajdorID, String tarea, Nombre nombre) {
        super(trabajdorID);
        this.nombre = nombre;
        this.listaTareas.add(tarea);
    }

    public void ejecutarTareas(){}

    public List<String> listaTareas(){
        return listaTareas;
    }

    public Nombre nombreTrabajador(){
        return nombre;
    }
}

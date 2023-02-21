package com.example.storecare.storecar.domain;

import com.example.storecare.storecar.domain.generic.Entity;
import com.example.storecare.storecar.domain.values.*;

public class Tarea extends Entity<TareaID> {

    private Descripcion descripcion;

    private final EncargadoID encargadoID;

    private Nombre nombre;

    private Boolean tareaFinalizada;

    private FechaCreacion fechaCreacion;

    public Tarea(TareaID tareaID, Descripcion descripcion, Nombre nombre, FechaCreacion fechaCreacion) {
        super(tareaID);
        this.descripcion = descripcion;
        this.encargadoID = new EncargadoID("");
        this.nombre = nombre;
        this.tareaFinalizada = false;
        this.fechaCreacion = fechaCreacion;
    }

    public void tareaFinalizada(){
        this.tareaFinalizada = true;
    }

    public void tareaNoFinalizada(){
        this.tareaFinalizada = false;
    }

    public void modificarDescripcion(Descripcion descripcion){
        this.descripcion = descripcion;
    }

    public void modificarNombreTarea(Nombre nombre){
        this.nombre = nombre;
    }

    public Boolean estadoTarea(){
        return this.tareaFinalizada;
    }

    public Descripcion descripcion(){
        return this.descripcion;
    }

    public Nombre nombreTarea(){
        return this.nombre;
    }
}

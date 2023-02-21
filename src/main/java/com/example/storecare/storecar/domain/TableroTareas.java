package com.example.storecare.storecar.domain;

import com.example.storecare.storecar.domain.generic.Entity;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TableroTareasID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableroTareas extends Entity<TableroTareasID> {

    protected List<Supervisor> listaSupevisores;
    protected List<Tarea> listaTareas;
    private final Nombre nombre;
    private final FechaCreacion fechaCreacion;
    public TableroTareas(TableroTareasID tableroTareasID, Nombre nombre, FechaCreacion fechaCreacion) {
        super(tableroTareasID);
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.listaSupevisores = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
    }

    public void asignarSupervisor(Supervisor supervisor){

        this.listaSupevisores.add(supervisor);
    }
    public void crearTareas(Tarea tarea){
        this.listaTareas.add(tarea);
    }

    public List<Supervisor> listaSupervisores(){
        return this.listaSupevisores;
    }

    public List<Tarea> listaTareas(){
        return this.listaTareas;
    }

    public FechaCreacion fechaCreacion(){
        return this.fechaCreacion;
    }
}

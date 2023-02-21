package com.example.storecare.storecar.domain;

import com.example.storecare.storecar.domain.generic.Entity;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorID;

import java.util.ArrayList;
import java.util.List;

public class Supervisor extends Entity<SupervisorID> {

    private final List<Trabajador> listaTrabajadores;
    private final Nombre nombre;
    public Supervisor(SupervisorID supervisorID, Nombre nombre) {
        super(supervisorID);
        this.nombre = nombre;
        this.listaTrabajadores = new ArrayList<>();
    }

    public void asignarTrabajador(Trabajador trabajador){
        this.listaTrabajadores.add(trabajador);
    }

    public void asignarTareas(List<Tarea> listaTareas){

    }

    public List<Trabajador> listaTrabajadores(){
        return this.listaTrabajadores;
    }

    public Nombre nombre(){
        return this.nombre;
    }
}

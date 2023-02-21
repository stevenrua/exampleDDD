package com.example.storecare.storecar.domain.events;

import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorID;
import com.example.storecare.storecar.domain.values.TableroTareasID;

public class SupervisorAsignado extends DomainEvent {
    private SupervisorID supervisorID;
    private Nombre nombre;
    private TableroTareasID tableroTareasID;


    public SupervisorAsignado(TableroTareasID tableroTareasID, SupervisorID supervisorID, Nombre nombre) {
        super("storecar.supervisorasignado");
        this.tableroTareasID = tableroTareasID;
        this.supervisorID = supervisorID;
        this.nombre = nombre;
    }

    public SupervisorID getSupervisorID() {
        return supervisorID;
    }

    public TableroTareasID getTableroTareasID() {
        return tableroTareasID;
    }

    public Nombre getNombre() {
        return nombre;
    }
}

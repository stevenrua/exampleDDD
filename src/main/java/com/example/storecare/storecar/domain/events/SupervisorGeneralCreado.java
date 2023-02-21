package com.example.storecare.storecar.domain.events;

import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TableroTareasID;

public class SupervisorGeneralCreado extends DomainEvent {
    private final Nombre nombre;
    private final TableroTareasID tableroTareasID;
    private final Nombre nombreTablero;
    private final FechaCreacion fechaCreacion;


    public SupervisorGeneralCreado(Nombre nombre, TableroTareasID tableroTareasID, Nombre nombreTablero, FechaCreacion fechaCreacion) {
        super("storecar.supervisorgeneralcreado");
        this.nombre = nombre;
        this.tableroTareasID = tableroTareasID;
        this.nombreTablero = nombreTablero;
        this.fechaCreacion = fechaCreacion;
    }

    public TableroTareasID getTableroTareasID() {
        return tableroTareasID;
    }

    public Nombre getNombreTablero() {
        return nombreTablero;
    }

    public FechaCreacion getFechaCreacion() {
        return fechaCreacion;
    }

    public Nombre getNombre() {
        return nombre;
    }
}

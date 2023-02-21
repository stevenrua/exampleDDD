package com.example.storecare.storecar.domain.events;

import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.*;

public class TareaCreada extends DomainEvent {
    private TareaID tareaID;
    private Descripcion descripcion;
    private Nombre nombre;
    private FechaCreacion fechaCreacion;
    private TableroTareasID tableroTareasID;
    public TareaCreada(TableroTareasID tableroTareasID,TareaID tareaID, Descripcion descripcion, Nombre nombre, FechaCreacion fechaCreacion) {
        super("storecar.tareacreada");
        this.tareaID = tareaID;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.tableroTareasID = tableroTareasID;
        System.out.println("se entra a crear la tarea");
    }

    public TareaCreada(TableroTareasID tableroTareasID, Descripcion descripcion, Nombre nombre, FechaCreacion fechaCreacion) {
        super("storecar.tareacreada");
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.tableroTareasID = tableroTareasID;
        System.out.println("se entra a crear la tarea");
    }

    public Descripcion getDescripcion() {
        return descripcion;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public TareaID getTareaID() {
        return tareaID;
    }

    public TableroTareasID getTableroTareasID() {
        return tableroTareasID;
    }

    public FechaCreacion getFechaCreacion() {
        return fechaCreacion;
    }
}

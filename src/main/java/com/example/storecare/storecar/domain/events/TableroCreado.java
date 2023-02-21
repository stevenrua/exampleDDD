package com.example.storecare.storecar.domain.events;

import com.example.storecare.storecar.domain.TableroTareas;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TableroTareasID;

import java.util.List;

public class TableroCreado extends DomainEvent {
    private TableroTareasID tableroTareasID;
    private Nombre nombre;
    private FechaCreacion fechaCreacion;
    public TableroCreado(TableroTareasID tableroTareasID, Nombre nombre, FechaCreacion fechaCreacion) {
        super("storecar.tablerocreado");
        this.tableroTareasID = tableroTareasID;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public TableroTareasID getTableroTareasID() {
        return tableroTareasID;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public FechaCreacion getFechaCreacion() {
        return fechaCreacion;
    }

}

package com.example.storecare.storecar.domain.command;

import co.com.sofka.domain.generic.Command;

public class CrearTableroTareas extends Command {
    private String tableroTareasId;
    private String supervisorGenetalId;
    private String nombre;
    private String area;
    private String fechaCreacion;

    public CrearTableroTareas() {}

    public CrearTableroTareas(String tableroTareasId, String supervisorGenetalId, String nombre, String area, String fechaCreacion) {
        this.tableroTareasId = tableroTareasId;
        this.supervisorGenetalId = supervisorGenetalId;
        this.nombre = nombre;
        this.area = area;
        this.fechaCreacion = fechaCreacion;
    }

    public String getTableroTareasId() {
        return tableroTareasId;
    }

    public void setTableroTareasId(String tableroTareasId) {
        this.tableroTareasId = tableroTareasId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getSupervisorGenetalId() {
        return supervisorGenetalId;
    }

    public void setSupervisorGenetalId(String supervisorGenetalId) {
        this.supervisorGenetalId = supervisorGenetalId;
    }
}

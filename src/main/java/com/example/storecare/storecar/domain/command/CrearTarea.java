package com.example.storecare.storecar.domain.command;

import co.com.sofka.domain.generic.Command;

public class CrearTarea extends Command {
    private String tareaId;
    private String supervisorGenetalId;
    private String tableroTareasId;
    private String descripcion;
    private String nombre;
    private String area;

    private String supervisorID;

    public CrearTarea(String tareaId, String supervisorGenetalId, String tableroTareasId,
                      String descripcion, String nombre, String area, String supervisorID) {
        this.tareaId = tareaId;
        this.supervisorGenetalId = supervisorGenetalId;
        this.tableroTareasId = tableroTareasId;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.area = area;
        this.supervisorID = supervisorID;
    }

    public CrearTarea() {}

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getSupervisorGenetalId() {
        return supervisorGenetalId;
    }

    public void setSupervisorGenetalId(String supervisorGenetalId) {
        this.supervisorGenetalId = supervisorGenetalId;
    }

    public String getTableroTareasId() {
        return tableroTareasId;
    }

    public void setTableroTareasId(String tableroTareasId) {
        this.tableroTareasId = tableroTareasId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
}

package com.example.storecare.storecar.domain.command;

import co.com.sofka.domain.generic.Command;

public class AsignarSupervisor extends Command {
    private String supervisorId;
    private String supervisorGenetalId;
    private String tableroTareasId;
    private String nombre;
    private String area;

    public AsignarSupervisor() {}

    public AsignarSupervisor(String supervisorId, String supervisorGenetalId, String tableroTareasId, String nombre, String area) {
        this.supervisorId = supervisorId;
        this.supervisorGenetalId = supervisorGenetalId;
        this.tableroTareasId = tableroTareasId;
        this.nombre = nombre;
        this.area = area;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
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

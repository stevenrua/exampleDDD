package com.example.storecare.storecar.domain.command;

import co.com.sofka.domain.generic.Command;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TableroTareasID;

public class CrearSupervisorGeneral extends Command{

    private String supervisorGenetalId;
    private String nombre;
    private String area;

    private String tableroTareasID;

    private String nombreTablero;

    private String areaTablero;

    private String fechaCreacion;

    public CrearSupervisorGeneral(){}

    public CrearSupervisorGeneral(String supervisorGenetalId, String nombre, String area) {
        this.supervisorGenetalId = supervisorGenetalId;
        this.nombre = nombre;
        this.area = area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSupervisorGenetalId() {
        return supervisorGenetalId;
    }

    public void setSupervisorGenetalId(String supervisorGenetalId) {
        this.supervisorGenetalId = supervisorGenetalId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTableroTareasID() {
        return tableroTareasID;
    }

    public String getNombreTablero() {
        return nombreTablero;
    }

    public String getAreaTablero() {
        return areaTablero;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
}

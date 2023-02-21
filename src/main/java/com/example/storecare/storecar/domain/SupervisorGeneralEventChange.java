package com.example.storecare.storecar.domain;

import com.example.storecare.storecar.domain.events.SupervisorAsignado;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.events.TableroCreado;
import com.example.storecare.storecar.domain.events.TareaCreada;
import com.example.storecare.storecar.domain.generic.EventChange;
import java.util.ArrayList;

public class SupervisorGeneralEventChange extends EventChange {
    public SupervisorGeneralEventChange(SupervisorGeneral supervisorGeneral){
        apply((SupervisorGeneralCreado event)->{
            supervisorGeneral.nombre = event.getNombre();
            supervisorGeneral.tableroTareas = new ArrayList<>();
        });

        apply((TableroCreado event)->{
            TableroTareas tableroTareas = new TableroTareas(event.getTableroTareasID(), event.getNombre(), event.getFechaCreacion());
            supervisorGeneral.tableroTareas.add(tableroTareas);
        });

        apply((SupervisorAsignado event)->{
            Supervisor supervisor = new Supervisor(event.getSupervisorID(), event.getNombre());
            TableroTareas tableroTareas = supervisorGeneral.tableroTareas.stream()
                    .filter(tablero -> tablero.identity().value()
                            .equals(event.getTableroTareasID().value())).findFirst().orElse(null);
            tableroTareas.asignarSupervisor(supervisor);
        });

        apply((TareaCreada event)->{
            Tarea tarea = new Tarea(event.getTareaID(), event.getDescripcion(), event.getNombre(), event.getFechaCreacion());
            TableroTareas tableroTareas = supervisorGeneral.tableroTareas.stream()
                    .filter(tablero -> tablero.identity().value()
                            .equals(event.getTableroTareasID().value())).findFirst().orElse(null);
            tableroTareas.crearTareas(tarea);
        });
    }
}
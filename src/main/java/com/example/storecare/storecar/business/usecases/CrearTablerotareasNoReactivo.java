package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.CrearTableroTareas;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import com.example.storecare.storecar.domain.values.TableroTareasID;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CrearTablerotareasNoReactivo implements UseCaseForCommandNoReactive<CrearTableroTareas>{

    private RepositoryExample repository;

    public CrearTablerotareasNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }
    @Override
    public List<DomainEvent> apply(CrearTableroTareas command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getSupervisorGenetalId());
        SupervisorGeneral supervisorGeneral = SupervisorGeneral
                .from(SupervisorGeneralID.of(command.getSupervisorGenetalId()), events);
        supervisorGeneral.crearTableroTareas(TableroTareasID.of(command.getTableroTareasId()),
                new Nombre(command.getNombre(), command.getArea()),
                new FechaCreacion(command.getSupervisorGenetalId()));
        return supervisorGeneral.getUncommittedChanges().stream().map(event->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}

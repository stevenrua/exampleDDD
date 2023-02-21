package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.CrearTarea;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class CrearTareaNoReactivo implements UseCaseForCommandNoReactive<CrearTarea>{

    private RepositoryExample repository;

    public CrearTareaNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(CrearTarea command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getSupervisorGenetalId());
        SupervisorGeneral supervisorGeneral = SupervisorGeneral
                .from(SupervisorGeneralID.of(command.getSupervisorGenetalId()), events);
        supervisorGeneral.crearTarea(TableroTareasID
                .of(command.getTableroTareasId()), TareaID.of(command.getTareaId()),
                new FechaCreacion(command.getSupervisorGenetalId()),
                new Nombre(command.getNombre(), command.getArea()),
                new Descripcion(command.getDescripcion()));
        return supervisorGeneral.getUncommittedChanges().stream().map(event->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}

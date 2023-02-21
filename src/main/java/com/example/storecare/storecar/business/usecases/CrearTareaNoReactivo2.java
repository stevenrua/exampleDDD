package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.events.TareaCreada;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.*;

import java.util.List;
import java.util.stream.Collectors;

public class CrearTareaNoReactivo2 implements UseCaseForEventNoReactive<TareaCreada>{

    private RepositoryExample repository;

    public CrearTareaNoReactivo2(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(TareaCreada event) {
        //List<DomainEvent> events = repository.findByIdNoReactivo(event.aggregateRootId());
        SupervisorGeneral supervisorGeneral = SupervisorGeneral
                .from(SupervisorGeneralID.of(event.aggregateRootId()), List.of(event));
        supervisorGeneral.crearTarea(event.getTableroTareasID(),
                event.getTareaID(),
                new FechaCreacion(event.aggregateRootId()),
                new Nombre(event.getNombre().value().nombre(), event.getNombre().value().area()),
                new Descripcion(event.getDescripcion().value().descripcionTarea()));
        return supervisorGeneral.getUncommittedChanges().stream().map(event1->{
            return repository.saveEventNoReactivo(event1);
        }).collect(Collectors.toList());
    }
}

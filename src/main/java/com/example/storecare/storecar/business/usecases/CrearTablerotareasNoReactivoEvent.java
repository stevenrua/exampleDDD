package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.events.TableroCreado;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CrearTablerotareasNoReactivoEvent implements UseCaseForEventNoReactive<SupervisorGeneralCreado>{

    private RepositoryExample repository;

    public CrearTablerotareasNoReactivoEvent(RepositoryExample repository) {
        this.repository = repository;
    }


    @Override
    public List<DomainEvent> apply(SupervisorGeneralCreado domainEvent) {
        SupervisorGeneral supervisorGeneral = SupervisorGeneral
                .from(SupervisorGeneralID.of(domainEvent.aggregateRootId()), List.of(domainEvent));
        supervisorGeneral.crearTableroTareas(domainEvent.getTableroTareasID(),
                domainEvent.getNombre(),
                domainEvent.getFechaCreacion());
        return supervisorGeneral.getUncommittedChanges().stream().map(event->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}

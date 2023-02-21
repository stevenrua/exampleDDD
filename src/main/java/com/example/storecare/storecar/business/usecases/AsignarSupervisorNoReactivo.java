package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.AsignarSupervisor;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import com.example.storecare.storecar.domain.values.SupervisorID;
import com.example.storecare.storecar.domain.values.TableroTareasID;

import java.util.List;
import java.util.stream.Collectors;

public class AsignarSupervisorNoReactivo implements UseCaseForCommandNoReactive<AsignarSupervisor>{
    private RepositoryExample repository;

    public AsignarSupervisorNoReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AsignarSupervisor command) {
        List<DomainEvent> events = repository.findByIdNoReactivo(command.getSupervisorGenetalId());
        SupervisorGeneral supervisorGeneral = SupervisorGeneral.from(SupervisorGeneralID.of(command.getSupervisorGenetalId()), events);
        supervisorGeneral.asignarSupervisor(TableroTareasID.of(command.getTableroTareasId()), SupervisorID.of(command.getSupervisorId()), new Nombre(command.getNombre(), command.getArea()));
        supervisorGeneral.asignarSupervisor(TableroTareasID.of(command.getTableroTareasId()), SupervisorID.of(command.getSupervisorId()), new Nombre(command.getNombre(), command.getArea()));
        return supervisorGeneral.getUncommittedChanges().stream().map(event->{
            return repository.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}

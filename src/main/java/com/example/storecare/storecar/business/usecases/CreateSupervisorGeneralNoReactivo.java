package com.example.storecare.storecar.business.usecases;

import co.com.sofka.domain.generic.Command;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.CrearSupervisorGeneral;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import com.example.storecare.storecar.domain.values.TableroTareasID;

import java.util.List;
import java.util.stream.Collectors;

public class CreateSupervisorGeneralNoReactivo implements UseCaseForCommandNoReactive{
    private RepositoryExample repositoryExample;

    public CreateSupervisorGeneralNoReactivo(RepositoryExample repositoryExample) {
        this.repositoryExample = repositoryExample;
    }

    @Override
    public List<DomainEvent> apply(Command command) {
        CrearSupervisorGeneral crearSupervisorGeneral = (CrearSupervisorGeneral) command;
        SupervisorGeneral supervisorGeneral =
                new SupervisorGeneral(SupervisorGeneralID.of(crearSupervisorGeneral.getSupervisorGenetalId()),
                new Nombre(crearSupervisorGeneral.getNombre(), crearSupervisorGeneral.getArea()),
                new TableroTareasID(),
                        new Nombre("nombretablero", "areatablero"),
                        new FechaCreacion(crearSupervisorGeneral.getSupervisorGenetalId()));
        return supervisorGeneral.getUncommittedChanges().stream().map(event -> {
            return repositoryExample.saveEventNoReactivo(event);
        }).collect(Collectors.toList());
    }
}

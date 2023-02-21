package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.CrearSupervisorGeneral;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.generic.USeCaseForCommand;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import com.example.storecare.storecar.domain.values.TableroTareasID;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CrearSupervisorGeneralReactivo extends USeCaseForCommand<CrearSupervisorGeneral> {

    private final RepositoryExample repository;

    public CrearSupervisorGeneralReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearSupervisorGeneral> crearSupervisorGeneralMono) {
        return crearSupervisorGeneralMono.flatMapIterable(command ->{
            SupervisorGeneral supervisorGeneral = new SupervisorGeneral(
                    SupervisorGeneralID.of(command.getSupervisorGenetalId()),
                    new Nombre(command.getNombre(), command.getArea()),
                    TableroTareasID.of(command.getTableroTareasID()),
                    new Nombre(command.getNombre(), command.getArea()),
                    new FechaCreacion(command.getSupervisorGenetalId()));
            return supervisorGeneral.getUncommittedChanges();
        }).flatMap(event ->repository.saveEvent(event));
    }
}

package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.CrearTarea;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.generic.USeCaseForCommand;
import com.example.storecare.storecar.domain.values.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CrearTareaReactivo extends USeCaseForCommand<CrearTarea> {

    private final RepositoryExample repository;

    public CrearTareaReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearTarea> crearTareaMono) {
        return crearTareaMono.flatMapMany(command-> repository.findById(command.getSupervisorGenetalId())
                .collectList()
                .flatMapIterable(events -> {
                    SupervisorGeneral supervisorGeneral = SupervisorGeneral.from(
                            SupervisorGeneralID.of(command.getSupervisorGenetalId()), events);
                    supervisorGeneral.crearTarea(TableroTareasID.of(command.getTableroTareasId()),
                            TareaID.of(command.getTareaId()),
                            new FechaCreacion(command.getSupervisorGenetalId()),
                            new Nombre(command.getNombre(), command.getArea()),
                            new Descripcion(command.getDescripcion()));
                    return supervisorGeneral.getUncommittedChanges();
                }).flatMap(event-> repository.saveEvent(event)));
    }
}

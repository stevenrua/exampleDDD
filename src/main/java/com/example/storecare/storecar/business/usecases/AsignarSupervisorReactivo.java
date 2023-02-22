package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.command.AsignarSupervisor;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.generic.USeCaseForCommand;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import com.example.storecare.storecar.domain.values.SupervisorID;
import com.example.storecare.storecar.domain.values.TableroTareasID;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AsignarSupervisorReactivo extends USeCaseForCommand<AsignarSupervisor> {

    private final RepositoryExample repository;

    public AsignarSupervisorReactivo(RepositoryExample repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AsignarSupervisor> asignarSupervisorMono) {
        return asignarSupervisorMono.flatMapMany(command-> repository.findById(command.getSupervisorGenetalId())
                .collectList()
                .flatMapIterable(events->{
                    SupervisorGeneral supervisorGeneral = SupervisorGeneral
                            .from(SupervisorGeneralID.of(command.getSupervisorGenetalId()), events);
                    supervisorGeneral.asignarSupervisor(
                            TableroTareasID.of(command.getTableroTareasId()),
                            SupervisorID.of(command.getSupervisorId()),
                            new Nombre(command.getNombre(), command.getArea()));
                    return supervisorGeneral.getUncommittedChanges();
                }).flatMap(event->repository.saveEvent(event)));
    }
}

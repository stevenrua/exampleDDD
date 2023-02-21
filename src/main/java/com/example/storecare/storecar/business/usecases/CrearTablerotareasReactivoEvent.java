package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.SupervisorGeneralID;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.function.Function;

@Component
public class CrearTablerotareasReactivoEvent implements Function<Mono<SupervisorGeneralCreado>, Flux<DomainEvent>> {

    private RepositoryExample repositoryExample;

    public CrearTablerotareasReactivoEvent(RepositoryExample repositoryExample) {
        this.repositoryExample = repositoryExample;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<SupervisorGeneralCreado> supervisorGeneralCreadoMono) {
        return supervisorGeneralCreadoMono.flatMapIterable(event->{
            SupervisorGeneral supervisorGeneral = SupervisorGeneral
                    .from(SupervisorGeneralID.of(event.aggregateRootId()), List.of(event));
            supervisorGeneral.crearTableroTareas(
                    event.getTableroTareasID(),
                    event.getNombreTablero(),
                    event.getFechaCreacion());
            return supervisorGeneral.getUncommittedChanges();
        }).flatMap(domainEvent -> repositoryExample.saveEvent(domainEvent));
    }
}

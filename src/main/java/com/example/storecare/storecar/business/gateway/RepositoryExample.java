package com.example.storecare.storecar.business.gateway;


import com.example.storecare.storecar.domain.SupervisorGeneral;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RepositoryExample {
    DomainEvent saveEventNoReactivo(DomainEvent event);

    List<DomainEvent> findByIdNoReactivo(String agregateRootId);

    Mono<DomainEvent> saveEvent(DomainEvent event);

    Flux<DomainEvent> findById(String agregateRootId);

    List<DomainEvent> findByIdNoReactivoTablero(String tableroTareasId);

    void deleteById(String id);

    Flux<SupervisorGeneral> findAll();


}

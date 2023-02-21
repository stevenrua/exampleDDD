package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UsecaseForCommandReactive <R extends DomainEvent>{
    Flux<DomainEvent> apply(Mono<R> rMono);
}

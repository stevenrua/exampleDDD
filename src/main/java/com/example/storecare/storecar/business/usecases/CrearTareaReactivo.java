package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.domain.command.CrearTarea;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.generic.USeCaseForCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CrearTareaReactivo extends USeCaseForCommand<CrearTarea> {
    @Override
    public Flux<DomainEvent> apply(Mono<CrearTarea> crearTareaMono) {
        return null;
    }
}

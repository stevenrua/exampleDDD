package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.events.TableroCreado;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TableroTareasID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CrearTablerotareasReactivoEventTest {

    @Mock
    private RepositoryExample repository;

    private CrearTablerotareasReactivoEvent useCase;

    @BeforeEach
    void setUp(){
        useCase = new CrearTablerotareasReactivoEvent(repository);
    }

    @Test
    void successFullScenario(){
        final String AGGREGATE_ID = "SupervisorGeneral-id-test";
        SupervisorGeneralCreado event = new SupervisorGeneralCreado(
                new Nombre("supervisorGeneral-test", "gerenciaGeneral-test"),
                TableroTareasID.of("tableroTareas-test"),
                new Nombre("nombreTablero-test", "asignacionTareas-test"),
                new FechaCreacion(AGGREGATE_ID)
        );
        event.setAggregateRootId(AGGREGATE_ID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(TableroCreado.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0))
        );

        Flux<DomainEvent> result = useCase.apply(Mono.just(event));

        StepVerifier.create(result)
                //Assert
                .expectNextMatches(event1 ->{
                    TableroCreado tableroCreado = (TableroCreado) event1;
                    Assertions.assertEquals(tableroCreado.getTableroTareasID(), event.getTableroTareasID());
                    Assertions.assertEquals(TableroCreado.class, event1.getClass());
                    Assertions.assertEquals(event.getNombreTablero().value().nombre(), tableroCreado.getNombre().value().nombre());
                    return event1.aggregateRootId().equals(event.aggregateRootId()); })
                .verifyComplete();
    }

}
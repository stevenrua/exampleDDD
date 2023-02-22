package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.CrearSupervisorGeneral;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.events.TableroCreado;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.FechaCreacion;
import com.example.storecare.storecar.domain.values.Nombre;
import com.example.storecare.storecar.domain.values.TableroTareasID;
import org.junit.jupiter.api.AfterEach;
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

@ExtendWith(MockitoExtension.class)
class CrearSupervisorGeneralReactivoTest {
    @Mock
    private RepositoryExample repository;

    private CrearSupervisorGeneralReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new CrearSupervisorGeneralReactivo(repository);
    }
    @Test
    void successfullScenario(){
        String SUPERVISORGENERALID = "test-supervisorgeneral-id";
        String NOMBRE = "supervisorgeneral";
        String AREA = "test-area";

        CrearSupervisorGeneral crearSupervisorGeneralCommand = new CrearSupervisorGeneral(
                SUPERVISORGENERALID, NOMBRE, AREA);

        SupervisorGeneralCreado supervisorGeneralCreadoEvent = new SupervisorGeneralCreado(
                new Nombre(NOMBRE, AREA),
                        TableroTareasID.of("tablerotareas-test"),
                        new Nombre("nombretablero-test", "areatablero-test"),
                        new FechaCreacion(SUPERVISORGENERALID));
        supervisorGeneralCreadoEvent.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(SupervisorGeneralCreado.class)))
                .thenAnswer(invocationOnMock -> {
                    return Mono.just(invocationOnMock.getArgument(0));
                });

        Flux<DomainEvent> result = useCase.apply(Mono.just(crearSupervisorGeneralCommand));
        StepVerifier.create(result)
                .expectNextMatches((event1)->{
                    Assertions.assertEquals(event1.getAggregateName(),
                            supervisorGeneralCreadoEvent.getNombre().value().nombre());
                    Assertions.assertEquals(event1.getClass(),
                            SupervisorGeneralCreado.class);
                    return event1.aggregateRootId().equals(supervisorGeneralCreadoEvent.aggregateRootId());})
                .verifyComplete();
    }
}
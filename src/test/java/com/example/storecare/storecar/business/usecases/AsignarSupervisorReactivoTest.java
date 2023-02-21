package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.AsignarSupervisor;
import com.example.storecare.storecar.domain.events.SupervisorAsignado;
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

@ExtendWith(MockitoExtension.class)
class AsignarSupervisorReactivoTest {

    @Mock
    private RepositoryExample repository;

    private AsignarSupervisorReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new AsignarSupervisorReactivo(repository);
    }

    @Test
    void successfullScenario(){
        String SUPERVISORID = "supervisor-test";
        String SUPERVISORGENERALID = "supervisorgeneralid";
        String TABLEROTAREASID = "tablerotareasid";
        String NOMBRE = "supervisorgeneral";
        String AREA = "recursos-test";

        //tablerocreado
        String NOMBRETABLERO = "tablerotareas-test";
        String AREATABLERO = "gerenciageneral-test";
        String FECHACREACION = "supervisorgeneralid-test";

        AsignarSupervisor command = new AsignarSupervisor(SUPERVISORID, SUPERVISORGENERALID,
                TABLEROTAREASID, NOMBRE, AREA);

        SupervisorGeneralCreado supervisorGeneralCreado = new SupervisorGeneralCreado(
                new Nombre(NOMBRE, AREA),
                new TableroTareasID(),
                new Nombre(NOMBRETABLERO, AREATABLERO),
                new FechaCreacion(FECHACREACION));
        supervisorGeneralCreado.setAggregateRootId(SUPERVISORGENERALID);

        TableroCreado tableroCreado =
                new TableroCreado(TableroTareasID.of(TABLEROTAREASID),
                        new Nombre(NOMBRETABLERO, AREATABLERO),
                        new FechaCreacion(FECHACREACION));
        tableroCreado.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.findById(SUPERVISORGENERALID))
                .thenReturn(Flux.just(supervisorGeneralCreado, tableroCreado));


        Mockito.when(repository.saveEvent(ArgumentMatchers.any(SupervisorAsignado.class)))
                .thenAnswer(interceptor -> {
                    return Mono.just(interceptor.getArgument(0));
                });

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result)
                .expectNextMatches((event1)->{
                    Assertions.assertEquals(event1.getAggregateName(), command.getNombre());
                    Assertions.assertEquals(event1.getClass(), SupervisorAsignado.class);
                    System.out.println(event1.aggregateRootId() + " " + command.getSupervisorId());
                    return event1.aggregateRootId().equals(command.getSupervisorGenetalId());})
                .verifyComplete();
    }

}
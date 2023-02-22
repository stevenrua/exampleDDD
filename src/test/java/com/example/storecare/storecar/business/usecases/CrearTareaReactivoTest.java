package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.CrearTarea;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.events.TableroCreado;
import com.example.storecare.storecar.domain.events.TareaCreada;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CrearTareaReactivoTest {

    @Mock
    private RepositoryExample repository;

    private CrearTareaReactivo useCase;

    @BeforeEach
    void setUp(){
        useCase = new CrearTareaReactivo(repository);
    }

    @Test
    void successFullScenario(){
        //creartarea
        String TAREAID = "tareaId-test";
        String SUPERVISORGENERALID = "supervisorGeneralId-test";
        String TABLEROTAREASID = "tableroTareasId-test";
        String DESCRIPCION = "descripciontareas-test";
        String NOMBRE = "supervisorgeneral";
        String AREA = "ejecuciontareas-test";
        String SUPERVISORID = "supervisorGeneralId-test";

        //tablerocreado
        String NOMBRETABLERO = "tablerotareas-test";
        String AREATABLERO = "gerenciageneral-test";
        String FECHACREACION = "supervisorgeneralid-test";

        CrearTarea command = new CrearTarea(TAREAID, SUPERVISORGENERALID, TABLEROTAREASID,
                DESCRIPCION, NOMBRE, AREA, SUPERVISORID);

        SupervisorGeneralCreado supervisorGeneralCreado = new SupervisorGeneralCreado(
                new Nombre("Steven-test", "areaingenieria-test"),
                new TableroTareasID(),
                new Nombre(NOMBRETABLERO, AREATABLERO),
                new FechaCreacion(FECHACREACION)
                );
        supervisorGeneralCreado.setAggregateRootId(SUPERVISORGENERALID);

        TableroCreado tableroCreado =
                new TableroCreado(TableroTareasID.of(TABLEROTAREASID),
                        new Nombre(NOMBRETABLERO, AREATABLERO),
                        new FechaCreacion(FECHACREACION));
        tableroCreado.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.findById(SUPERVISORGENERALID))
                .thenReturn(Flux.just(supervisorGeneralCreado, tableroCreado));

        Mockito.when(repository.saveEvent(ArgumentMatchers.any(TareaCreada.class)))
                .thenAnswer(interceptor -> {
                    return Mono.just(interceptor.getArgument(0));
                });

        Flux<DomainEvent> result = useCase.apply(Mono.just(command));
        StepVerifier.create(result)
                .expectNextMatches((event1)->{
                    Assertions.assertEquals(event1.getAggregateName(),
                            command.getNombre());
                    Assertions.assertEquals(event1.getClass(),
                            TareaCreada.class);
                    return event1.aggregateRootId().equals(command.getSupervisorGenetalId());})
                .verifyComplete();
    }

}
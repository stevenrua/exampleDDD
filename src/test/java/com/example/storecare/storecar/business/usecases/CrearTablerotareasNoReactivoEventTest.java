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
import reactor.core.publisher.Mono;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CrearTablerotareasNoReactivoEventTest {

    @Mock
    private RepositoryExample repository;

    private CrearTablerotareasNoReactivoEvent useCase;

    @BeforeEach
    void setUp(){
        useCase = new CrearTablerotareasNoReactivoEvent(repository);
    }

    @Test
    void successFullScenario(){
        final String AGGREGATE_ID = "test-post-id";
        SupervisorGeneralCreado event = new SupervisorGeneralCreado(
                new Nombre("supervisorgeneral-test", "supervición-test"),
                TableroTareasID.of("tableroId-test"),
                new Nombre("nombretablero-test", "asignación-test"),
                new FechaCreacion(AGGREGATE_ID)
        );

        event.setAggregateRootId(AGGREGATE_ID);

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(TableroCreado.class))).thenAnswer(
                invocationOnMock -> invocationOnMock.getArgument(0)
        );

        List<DomainEvent> result = useCase.apply(event);

        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());
        Assertions.assertEquals("storecar.tablerocreado", result.get(0).type);
        Assertions.assertInstanceOf(TableroCreado.class, result.get(0));
    }

}
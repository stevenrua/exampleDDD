package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.CrearTarea;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
import com.example.storecare.storecar.domain.events.TableroCreado;
import com.example.storecare.storecar.domain.events.TareaCreada;
import com.example.storecare.storecar.domain.generic.DomainEvent;
import com.example.storecare.storecar.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CrearTareaNoReactivo2Test     {

    @Mock
    private RepositoryExample repository;

    private CrearTareaNoReactivo2 useCase;

    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new CrearTareaNoReactivo2(repository);
    }

    @Test
    void successfullScenario(){
        String TAREAID = "tareaId-test";
        String SUPERVISORGENERALID = "supervisorGeneralId-test";
        String TABLEROTAREASID = "tableroTareasId-test";
        String DESCRIPCION = "descripciontareas-test";
        String NOMBRE = "tarea-test";
        String AREA = "ejecuciontareas-test";
        String SUPERVISORID = "supervisorGeneralId-test";

        //tablerocreado
        String NOMBRETABLERO = "tablerotareas-test";
        String AREATABLERO = "gerenciageneral-test";
        String FECHACREACION = "supervisorgeneralid-test";

        SupervisorGeneralCreado supervisorGeneralCreado = new SupervisorGeneralCreado
                (new Nombre("Steven-test", "areaingenieria-test"),
                        new TableroTareasID(),
                        new Nombre("nombretablero-test", "areatablero-test"),
                        new FechaCreacion(SUPERVISORGENERALID));
        supervisorGeneralCreado.setAggregateRootId(SUPERVISORGENERALID);

        TableroCreado tableroCreado = new TableroCreado(TableroTareasID.of(TABLEROTAREASID),
                new Nombre(NOMBRETABLERO, AREATABLERO),
                new FechaCreacion(FECHACREACION));
        tableroCreado.setAggregateRootId(SUPERVISORGENERALID);

        TareaCreada event = new TareaCreada(tableroCreado.getTableroTareasID(),
                new TareaID(TAREAID),
                new Descripcion(DESCRIPCION),
                new Nombre(NOMBRE, AREA), new FechaCreacion(SUPERVISORID));
        event.setAggregateRootId(SUPERVISORGENERALID);

        /*Mockito.when(repository.findByIdNoReactivo(SUPERVISORGENERALID))
                .thenReturn(List.of(supervisorGeneralCreado, tableroCreado));*/

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(TareaCreada.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(event);

        //Assert
        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());
        Assertions.assertEquals("storecar.tareacreada", result.get(0).type);
        Assertions.assertInstanceOf(TareaCreada.class, result.get(0));
    }
}
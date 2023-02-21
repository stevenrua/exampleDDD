package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.CrearTarea;
import com.example.storecare.storecar.domain.events.SupervisorAsignado;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CrearTareaNoReactivoTest {
    @Mock
    private RepositoryExample repository;
    private CrearTareaNoReactivo useCase;
    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new CrearTareaNoReactivo(repository);
    }

    @Test
    void successfullScenario(){
        //creartarea
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

        CrearTarea command = new CrearTarea(TAREAID, SUPERVISORGENERALID, TABLEROTAREASID,
                DESCRIPCION, NOMBRE, AREA, SUPERVISORID);

        SupervisorGeneralCreado supervisorGeneralCreado =
                new SupervisorGeneralCreado
                (new Nombre("Steven-test", "areaingenieria-test"),
                        new TableroTareasID(),
                        new Nombre("nombretablerotest", "areatablerotest"),
                        new FechaCreacion(SUPERVISORGENERALID));
        supervisorGeneralCreado.setAggregateRootId(SUPERVISORGENERALID);

        TableroCreado tableroCreado =
                new TableroCreado(TableroTareasID.of(TABLEROTAREASID),
                new Nombre(NOMBRETABLERO, AREATABLERO),
                new FechaCreacion(FECHACREACION));
        tableroCreado.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.findByIdNoReactivo(SUPERVISORGENERALID))
                .thenReturn(List.of(supervisorGeneralCreado, tableroCreado));


        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(TareaCreada.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(command);

        //Assert
        Assertions.assertEquals(command.getSupervisorGenetalId(), result.get(0).aggregateRootId());
        Assertions.assertEquals("storecar.tareacreada", result.get(0).type);
        Assertions.assertInstanceOf(TareaCreada.class, result.get(0));
        Mockito.verify(repository, Mockito.times(1))
                .saveEventNoReactivo(ArgumentMatchers.any(TareaCreada.class));

    }
}
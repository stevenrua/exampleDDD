package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.AsignarSupervisor;
import com.example.storecare.storecar.domain.events.*;
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

@ExtendWith(MockitoExtension.class)
class AsignarSupervisorNoReactivoTest {

    @Mock
    private RepositoryExample repository;
    private AsignarSupervisorNoReactivo useCase;
    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new AsignarSupervisorNoReactivo(repository);
    }

    @Test
    void successfullScenario(){
        //asignarsupervisor
        String SUPERVISORID = "supervisor-test";
        String SUPERVISORGENERALID = "supervisorgeneralid";
        String TABLEROTAREASID = "tablerotareasid";
        String NOMBRE = "alejosupervisor-test";
        String AREA = "recursos-test";

        //tablerocreado
        String NOMBRETABLERO = "tablerotareas-test";
        String AREATABLERO = "gerenciageneral-test";
        String FECHACREACION = "supervisorgeneralid-test";

        AsignarSupervisor command = new AsignarSupervisor(SUPERVISORID, SUPERVISORGENERALID,
                TABLEROTAREASID, NOMBRE, AREA);

        SupervisorGeneralCreado supervisorGeneralCreado = new SupervisorGeneralCreado(
                new Nombre("Steven-test", "areaingenieria-test"),
                new TableroTareasID(),
                new Nombre(NOMBRETABLERO, AREATABLERO),
                new FechaCreacion(SUPERVISORGENERALID)
        );
        supervisorGeneralCreado.setAggregateRootId(SUPERVISORGENERALID);

        TableroCreado tableroCreado =
                new TableroCreado(TableroTareasID.of(TABLEROTAREASID),
                new Nombre(NOMBRETABLERO, AREATABLERO),
                new FechaCreacion(FECHACREACION));
        tableroCreado.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.findByIdNoReactivo(SUPERVISORGENERALID))
                .thenReturn(List.of(supervisorGeneralCreado, tableroCreado));


        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(SupervisorAsignado.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(command);

        //Assert
        Assertions.assertEquals(command.getSupervisorGenetalId(), result.get(0).aggregateRootId());
        Assertions.assertEquals("storecar.supervisorasignado", result.get(0).type);
        Assertions.assertInstanceOf(SupervisorAsignado.class, result.get(0));

        Mockito.verify(repository, Mockito.times(2))
                .saveEventNoReactivo(ArgumentMatchers.any(SupervisorAsignado.class));
        Mockito.verify(repository, Mockito.times(1))
                .findByIdNoReactivo("supervisorgeneralid");
    }
}
package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.CrearSupervisorGeneral;
import com.example.storecare.storecar.domain.events.SupervisorAsignado;
import com.example.storecare.storecar.domain.events.SupervisorGeneralCreado;
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
class CreateSupervisorGeneralNoReactivoTest {
    @Mock
    private RepositoryExample repository;
    private CreateSupervisorGeneralNoReactivo useCase;

    @BeforeEach
    void setUp(){
        //Arrange
        useCase = new CreateSupervisorGeneralNoReactivo(repository);
    }

    @Test
    void successfullScenario(){
        //Crear Supervisorgeneral
        String SUPERVISORGENERALID = "test-supervisorgeneral-id";
        String NOMBRE = "test-area";
        String AREA = "test-area";

        //Crear tablero


        //Arrage
        CrearSupervisorGeneral command = new CrearSupervisorGeneral(SUPERVISORGENERALID,
                NOMBRE, AREA);
        SupervisorGeneralCreado event = new SupervisorGeneralCreado(
                new Nombre(NOMBRE, AREA),
                TableroTareasID.of("tablero-id-test"),
                new Nombre("nombretablero-test", "areatablerotest"),
                new FechaCreacion(SUPERVISORGENERALID));
        event.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(SupervisorGeneralCreado.class)))
                .thenAnswer(invocationOnMock -> {
                    return invocationOnMock.getArgument(0);
                });
        Mockito.verify(repository, Mockito.times(0))
                .saveEventNoReactivo(Mockito.any());
        //Act
        List<DomainEvent> result = useCase.apply(command);
        //Asert
        Assertions.assertEquals(event.aggregateRootId(), result.get(0).aggregateRootId());
        Mockito.verify(repository, Mockito.times(1))
                .saveEventNoReactivo(ArgumentMatchers.any(SupervisorGeneralCreado.class));

    }
}
package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.business.gateway.RepositoryExample;
import com.example.storecare.storecar.domain.command.CrearTableroTareas;
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
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CrearTablerotareasNoReactivoTest {
    @Mock
    private RepositoryExample repository;
    private CrearTablerotareasNoReactivo useCase;

    @BeforeEach
    void setUp(){
    //Arrange
    useCase = new CrearTablerotareasNoReactivo(repository);
    }

    @Test
    void successfullScenario(){
        //Arrange
        String TABLEROTAREASID = "tablerotareasid-test";
        String SUPERVISORGENERALID = "supervisorgeneralid-test";
        String NOMBRE = "tablerotareas-test";
        String AREA = "gerenciageneral-test";
        String FECHACREACION = "supervisorgeneralid-test";

        CrearTableroTareas command = new CrearTableroTareas(TABLEROTAREASID,
                SUPERVISORGENERALID, NOMBRE, AREA, FECHACREACION);

        SupervisorGeneralCreado supervisorGeneralCreado = new SupervisorGeneralCreado(
            new Nombre("Steven-test", "areaingenieria-test"),
                new TableroTareasID(),
                new Nombre("nombretablero-test", "areatablerotest"),
                new FechaCreacion(SUPERVISORGENERALID)
        );
        supervisorGeneralCreado.setAggregateRootId(SUPERVISORGENERALID);

        Mockito.when(repository.findByIdNoReactivo(SUPERVISORGENERALID))
                .thenReturn(List.of(supervisorGeneralCreado));

        Mockito.when(repository.saveEventNoReactivo(ArgumentMatchers.any(TableroCreado.class)))
                .thenAnswer(interceptor -> {
                    return interceptor.getArgument(0);
                });

        //Act
        List<DomainEvent> result = useCase.apply(command);


        //Assert
        Assertions.assertEquals(command.getSupervisorGenetalId(), result.get(0).aggregateRootId());
        Assertions.assertInstanceOf(TableroCreado.class, result.get(0));
    }
}
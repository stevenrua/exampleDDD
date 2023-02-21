package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.ValueObject;

import java.util.Objects;

public class Descripcion implements ValueObject<Descripcion.Props> {
    private final String descripcionTarea;

    public Descripcion(String descripcionTarea){
        this.descripcionTarea = Objects.requireNonNull(descripcionTarea);
    }

    public interface Props {
        String descripcionTarea();
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String descripcionTarea() {
                return descripcionTarea;
            }
        };
    }
}

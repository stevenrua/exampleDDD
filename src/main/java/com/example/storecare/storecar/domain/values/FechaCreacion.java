package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.ValueObject;

import java.util.Date;
import java.util.Objects;

public class FechaCreacion implements ValueObject<FechaCreacion.Props> {
    private final Date date;
    private final String supervisorId;

    public FechaCreacion(String supervisorId){
        this.date = new Date();
        this.supervisorId = Objects.requireNonNull(supervisorId);
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String supervisorId() {
                return supervisorId;
            }

            @Override
            public Date date() {
                return date;
            }
        };
    }

    public interface Props {
        String supervisorId();
        Date date();

    }
}

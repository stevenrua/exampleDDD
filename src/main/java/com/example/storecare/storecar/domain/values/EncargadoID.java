package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.ValueObject;

public class EncargadoID implements ValueObject<String> {
    private final String encargadoID;

    public EncargadoID(String encargadoID){
        this.encargadoID = encargadoID;
    }

    @Override
    public String value() {
        return encargadoID;
    }
}

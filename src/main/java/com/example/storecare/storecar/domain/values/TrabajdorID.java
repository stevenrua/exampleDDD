package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.Identity;

public class TrabajdorID extends Identity {
    public TrabajdorID(String trabajdorID) {
        super(trabajdorID);
    }

    public TrabajdorID() {}

    public static TrabajdorID of(String trabajdorID) {
        return new TrabajdorID(trabajdorID);
    }
}

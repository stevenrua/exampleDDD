package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.Identity;

public class TareaID extends Identity {
    public TareaID(String tareaID) {
        super(tareaID);
    }

    public TareaID() {}

    public static TareaID of(String tareaID) {
        return new TareaID(tareaID);
    }
}

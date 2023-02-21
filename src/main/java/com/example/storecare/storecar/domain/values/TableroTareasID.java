package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.Identity;
import com.example.storecare.storecar.domain.generic.ValueObject;

public class TableroTareasID extends Identity {
    public TableroTareasID(String tableroTareasID) {
        super(tableroTareasID);
    }

    public TableroTareasID() {}

    public static TableroTareasID of(String tableroTareasID) {
        return new TableroTareasID(tableroTareasID);
    }
}

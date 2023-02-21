package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.Identity;

public class SupervisorID extends Identity {
    public SupervisorID(String supervisorID) {
        super(supervisorID);
    }

    public SupervisorID() {}

    public static SupervisorID of(String supervisorID) {
        return new SupervisorID(supervisorID);
    }
}

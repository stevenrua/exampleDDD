package com.example.storecare.storecar.domain.values;

import com.example.storecare.storecar.domain.generic.Identity;

public  class SupervisorGeneralID extends Identity {

    public SupervisorGeneralID(){}

    private SupervisorGeneralID(String id){
        super(id);
    }

    public static SupervisorGeneralID of(String supervisorGeneralID) {
        return new SupervisorGeneralID(supervisorGeneralID);
    }
}

package com.example.storecare.storecar.business.usecases;

import com.example.storecare.storecar.domain.generic.DomainEvent;

import java.util.List;

public interface UseCaseForEventNoReactive <R extends DomainEvent>{
    List<DomainEvent> apply(R domainEvent);
}

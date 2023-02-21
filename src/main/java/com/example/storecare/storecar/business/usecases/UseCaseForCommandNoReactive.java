package com.example.storecare.storecar.business.usecases;

import co.com.sofka.domain.generic.Command;
import com.example.storecare.storecar.domain.generic.DomainEvent;


import java.util.List;

public interface UseCaseForCommandNoReactive <R extends Command>{
    List<DomainEvent> apply(R command);
}

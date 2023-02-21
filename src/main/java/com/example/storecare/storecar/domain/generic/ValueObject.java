package com.example.storecare.storecar.domain.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}

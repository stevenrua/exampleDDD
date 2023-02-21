package com.example.storecare.storecar.domain.generic;

import java.util.Objects;
import java.util.UUID;

public class Identity implements ValueObject<String>{
    private final String uuid;

    public Identity() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Identity(String uuid) {
        this.uuid = uuid;
    }

    public String value() {
        return this.uuid;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Identity identity = (Identity)o;
            return Objects.equals(this.uuid, identity.uuid);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.uuid});
    }
}

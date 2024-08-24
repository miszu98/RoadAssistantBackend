package io.malek;


import com.fasterxml.jackson.annotation.JsonValue;


import java.util.Objects;

import static java.util.Objects.isNull;

public record Latitude(@JsonValue Double value) {

    public Latitude {
        if (isNull(value)) {
            throw new IllegalArgumentException("Latitude value cannot be null");
        }
    }

    public static Latitude of(Double value) {
        return new Latitude(value);
    }

    public static Latitude zero() {
        return new Latitude(0d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Latitude latitude = (Latitude) o;

        return Objects.equals(value, latitude.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

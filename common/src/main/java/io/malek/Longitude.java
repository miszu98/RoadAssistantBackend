package io.malek;

import com.fasterxml.jackson.annotation.JsonValue;


import java.util.Objects;

import static java.util.Objects.isNull;

public record Longitude(@JsonValue Double value) {

    public Longitude {
        if (isNull(value)) {
            throw new IllegalArgumentException("Longitude value cannot be null");
        }
    }

    public static Longitude of(Double value) {
        return new Longitude(value);
    }

    public static Longitude zero() {
        return new Longitude(0d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Longitude longitude = (Longitude) o;

        return Objects.equals(value, longitude.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}


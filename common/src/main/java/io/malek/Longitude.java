package io.malek;

import static java.util.Objects.isNull;

public record Longitude(Double value) {

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

}


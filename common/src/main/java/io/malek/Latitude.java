package io.malek;


import static java.util.Objects.isNull;

public record Latitude(Double value) {

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

}

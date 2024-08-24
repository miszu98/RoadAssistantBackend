package io.malek;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record LastName(String value) {

    public LastName {
        if (isBlank(value)) {
            throw new IllegalArgumentException("LastName cannot be null or empty string");
        }
    }

    public static LastName of(String value) {
        return new LastName(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LastName lastName = (LastName) o;

        return Objects.equals(value, lastName.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

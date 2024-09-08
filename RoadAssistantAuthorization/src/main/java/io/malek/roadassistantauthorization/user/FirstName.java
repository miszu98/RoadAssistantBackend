package io.malek.roadassistantauthorization.user;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record FirstName(String value) {

    public FirstName {
        if (isBlank(value)) {
            throw new IllegalArgumentException("FirstName cannot be null or empty string");
        }
    }

    @JsonCreator
    public static FirstName of(String value) {
        return new FirstName(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirstName firstName = (FirstName) o;

        return Objects.equals(value, firstName.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

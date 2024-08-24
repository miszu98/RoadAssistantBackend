package io.malek;


import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.isNull;

public record IncidentTime(@JsonValue LocalDateTime value) {

    public IncidentTime(String value) {
        this(LocalDateTime.parse(value));
    }

    public IncidentTime {
        if (isNull(value)) {
            throw new IllegalArgumentException("Incident time cannot be null");
        }
    }

    public static IncidentTime of(LocalDateTime value) {
        return new IncidentTime(value);
    }

    public static IncidentTime now() {
        return new IncidentTime(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncidentTime that = (IncidentTime) o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

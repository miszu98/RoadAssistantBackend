package io.malek;


import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDateTime;

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

}

package io.malek;


import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public record IncidentTime(LocalDateTime value) {

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

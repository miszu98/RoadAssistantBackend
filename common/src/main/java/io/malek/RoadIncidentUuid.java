package io.malek;

import java.util.UUID;

import static java.util.Objects.isNull;

public record RoadIncidentUuid(UUID value) {

    public RoadIncidentUuid {
        if (isNull(value)) {
            throw new IllegalArgumentException("Road incident uuid cannot be null");
        }
    }

    public static RoadIncidentUuid newOne() {
        return new RoadIncidentUuid(UUID.randomUUID());
    }

    public static RoadIncidentUuid of(UUID value) {
        return new RoadIncidentUuid(value);
    }

}

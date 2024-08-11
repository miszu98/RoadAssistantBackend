package io.malek;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

public record RoadIncident(LocalDateTime time, long lon, long lat) {

    public RoadIncident {
        if (isNull(time)) {
            throw new IllegalArgumentException("Time in road incident model cannot be null");
        }
    }

    public static RoadIncident of(LocalDateTime time, long lon, long lat) {
        return new RoadIncident(time, lon, lat);
    }

    public static RoadIncident now(long lon, long lat) {
        return new RoadIncident(LocalDateTime.now(), lon, lat);
    }

}

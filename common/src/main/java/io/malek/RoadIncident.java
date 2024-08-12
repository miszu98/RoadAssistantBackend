package io.malek;

import com.fasterxml.jackson.annotation.JsonFormat;

import static java.util.Objects.isNull;

public record RoadIncident(RoadIncidentUuid uuid, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") IncidentTime incidentTime,
                           Longitude longitude, Latitude latitude) {

    public RoadIncident {
        if (isNull(incidentTime)) {
            throw new IllegalArgumentException("Time in road incident model cannot be null");
        }
    }

    public static RoadIncident of(RoadIncidentUuid roadIncidentUuid, IncidentTime incidentTime, Longitude longitude, Latitude latitude) {
        return new RoadIncident(roadIncidentUuid, incidentTime, longitude, latitude);
    }

    public static RoadIncident of(IncidentTime incidentTime, Longitude longitude, Latitude latitude) {
        return new RoadIncident(RoadIncidentUuid.newOne(), incidentTime, longitude, latitude);
    }

    public static RoadIncident now(Longitude longitude, Latitude latitude) {
        return new RoadIncident(RoadIncidentUuid.newOne(), IncidentTime.now(), longitude, latitude);
    }

}

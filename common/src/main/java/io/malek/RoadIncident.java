package io.malek;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadIncident that = (RoadIncident) o;

        if (!Objects.equals(uuid, that.uuid)) return false;
        if (!Objects.equals(incidentTime, that.incidentTime)) return false;
        if (!Objects.equals(longitude, that.longitude)) return false;
        return Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (incidentTime != null ? incidentTime.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }
}

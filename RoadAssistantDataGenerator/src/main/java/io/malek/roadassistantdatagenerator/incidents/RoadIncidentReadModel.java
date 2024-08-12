package io.malek.roadassistantdatagenerator.incidents;

import java.time.LocalDateTime;
import java.util.UUID;

interface RoadIncidentReadModel {
    UUID getUuid();
    Double getLongitude();
    Double getLatitude();
    LocalDateTime getIncidentTime();
}

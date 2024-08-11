package io.malek.roadassistantdatagenerator.incidents;

import io.malek.RoadIncident;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(staticName = "assertThatRoadIncident")
class RoadIncidentAssert {

    private final RoadIncident roadIncident;

    RoadIncidentAssert isCorrectGeneratedRoadIncident(List<RoadIncidentEntity> roadIncidents) {
        assertNotNull(roadIncident.latitude());
        assertNotNull(roadIncident.longitude());
        assertNotNull(roadIncident.incidentTime());
        assertFalse(roadIncidents.isEmpty());
        return this;
    }

    RoadIncidentAssert isCorrectGeneratedRoadIncident() {
        assertNotNull(roadIncident.latitude());
        assertNotNull(roadIncident.longitude());
        assertNotNull(roadIncident.incidentTime());
        return this;
    }

}

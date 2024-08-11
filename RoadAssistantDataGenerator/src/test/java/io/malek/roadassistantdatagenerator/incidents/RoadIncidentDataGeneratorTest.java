package io.malek.roadassistantdatagenerator.incidents;

import io.malek.RoadIncident;
import org.junit.jupiter.api.Test;

import static io.malek.roadassistantdatagenerator.incidents.RoadIncidentAssert.*;

class RoadIncidentDataGeneratorTest {

    @Test
    void shouldGenerateIncidentCords() {
        RoadIncident roadIncident = RoadIncidentDataGenerator.generateIncidentCords();
        assertThatRoadIncident(roadIncident).isCorrectGeneratedRoadIncident();
    }

}

package io.malek.roadassistantdatagenerator.incidents;

import io.malek.RoadIncident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.malek.roadassistantdatagenerator.incidents.RoadIncidentAssert.*;

class RoadIncidentFacadeTest {
    RoadIncidentRepository roadIncidentRepository;
    RoadIncidentFacade roadIncidentFacade;

    @BeforeEach
    void setUp() {
        roadIncidentRepository = new InMemoryRoadIncidentRepository();
        RoadIncidentConfiguration roadIncidentConfiguration = new RoadIncidentConfiguration(roadIncidentRepository);
        roadIncidentFacade = roadIncidentConfiguration.roadIncidentFacade();
    }

    @Test
    void shouldSaveRoadIncident() {
        RoadIncident roadIncident = roadIncidentFacade.generateIncident();
        assertThatRoadIncident(roadIncident).isCorrectGeneratedRoadIncident(roadIncidentRepository.findAll());
    }

}

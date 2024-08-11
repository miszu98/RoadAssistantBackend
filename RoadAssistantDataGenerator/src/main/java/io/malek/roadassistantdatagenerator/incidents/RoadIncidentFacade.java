package io.malek.roadassistantdatagenerator.incidents;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.RoadIncident;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
class RoadIncidentFacade {
    private final RoadIncidentRepository roadIncidentRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public RoadIncident generateIncident() {
        RoadIncident roadIncident = RoadIncidentDataGenerator.generateIncidentCords();
        log.info("Mapping road incident to entity with cords: [{}, {}] and time: [{}]", roadIncident.latitude(), roadIncident.longitude(), roadIncident.incidentTime());
        RoadIncidentEntity roadIncidentEntity = objectMapper.convertValue(roadIncident, RoadIncidentEntity.class);
        log.info("Road incident saved");
        roadIncidentRepository.save(roadIncidentEntity);
        return roadIncident;
    }

}

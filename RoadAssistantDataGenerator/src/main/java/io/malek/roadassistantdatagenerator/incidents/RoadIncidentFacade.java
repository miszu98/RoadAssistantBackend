package io.malek.roadassistantdatagenerator.incidents;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<RoadIncident> findRoadIncidentsByDate(IncidentTime incidentTime, Pageable pageable) {
        log.info("Searching road incidents for: [{}]", incidentTime.value());
        LocalDateTime localDateTime = incidentTime.value();
        LocalDate localDate = localDateTime.toLocalDate();
        List<RoadIncidentReadModel> roadIncidentReadModels = roadIncidentRepository.findRoadIncidentByTime(localDate);
        return mapToRoadIncidents(roadIncidentReadModels, pageable);
    }

    private List<RoadIncident> mapToRoadIncidents(List<RoadIncidentReadModel> roadIncidentReadModels, Pageable pageable) {
        log.info("Mapping loaded road incidents read models: [{}]", roadIncidentReadModels.size());
        List<RoadIncident> roadIncidents = roadIncidentReadModels.stream().map(this::mapToRoadIncident).toList();
        log.info("Creating new page with mapped read models to road incidents dto");
        return roadIncidents;
    }

    private RoadIncident mapToRoadIncident(RoadIncidentReadModel rm) {
        return RoadIncident.of(RoadIncidentUuid.of(rm.getUuid()), IncidentTime.of(rm.getIncidentTime()),
                Longitude.of(rm.getLongitude()), Latitude.of(rm.getLatitude()));
    }

}

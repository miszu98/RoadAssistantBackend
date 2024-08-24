package io.malek.roadassistantdatagenerator.incidents;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

interface RoadIncidentRepository {

    RoadIncidentEntity save(RoadIncidentEntity entity);

    List<RoadIncidentEntity> findAll();

    @Query(value = "select uuid, longitude, latitude, incident_time from road_incidents where incident_time::date = :incidentTime",
            nativeQuery = true)
    List<RoadIncidentReadModel> findRoadIncidentByTime(LocalDate incidentTime);
}

package io.malek.roadassistantdatagenerator.incidents;

import io.malek.RoadIncident;
import io.vavr.control.Try;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;

class InMemoryRoadIncidentRepository implements RoadIncidentRepository {
    private final Map<Long, RoadIncidentEntity> db = new HashMap<>();

    @Override
    public RoadIncidentEntity save(RoadIncidentEntity entity) {
        Long identifier = getNextIdentifier();
        entity.setId(identifier);
        db.put(identifier, entity);
        return entity;
    }

    @Override
    public List<RoadIncidentEntity> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public Page<RoadIncidentReadModel> findRoadIncidentByTime(LocalDate incidentTime, Pageable pageable) {
        return null;
    }

    private Long getNextIdentifier() {
        List<Long> identifiers = db.keySet().stream().sorted().toList();
        return Try.of(() -> identifiers.reversed().get(0) + 1).getOrElse(1L);
    }
}

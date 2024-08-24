package io.malek.roadassistantdatagenerator.incidents;

import io.vavr.control.Try;

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
    public List<RoadIncidentReadModel> findRoadIncidentByTime(LocalDate incidentTime) {
        return null;
    }

    private Long getNextIdentifier() {
        List<Long> identifiers = db.keySet().stream().sorted().toList();
        return Try.of(() -> identifiers.reversed().get(0) + 1).getOrElse(1L);
    }
}

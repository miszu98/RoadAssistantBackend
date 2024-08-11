package io.malek.roadassistantdatagenerator.incidents;

import java.util.List;

interface RoadIncidentRepository {
    RoadIncidentEntity save(RoadIncidentEntity entity);
    List<RoadIncidentEntity> findAll();
}

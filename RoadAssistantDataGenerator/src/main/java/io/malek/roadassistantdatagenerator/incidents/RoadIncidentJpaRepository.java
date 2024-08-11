package io.malek.roadassistantdatagenerator.incidents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface RoadIncidentJpaRepository extends JpaRepository<RoadIncidentEntity, Long>, RoadIncidentRepository {

}

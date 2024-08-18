package io.malek.roadassistant;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

interface RoadIncidentApi<T> {

    T refreshRoadIncidents(LocalDate incidentTime, Pageable pageable);

}

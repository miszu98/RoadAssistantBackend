package io.malek.roadassistant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

interface RoadIncidentExternalApiClient<T> {

    Page<T> getRoadIncidents(LocalDate incidentTime, Pageable pageable);

    ApiSourceName getApiSourceName();

}

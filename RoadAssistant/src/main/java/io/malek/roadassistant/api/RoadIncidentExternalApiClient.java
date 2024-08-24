package io.malek.roadassistant.api;

import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

interface RoadIncidentExternalApiClient<T> {

    List<T> getRoadIncidents(LocalDate incidentTime);

    ApiSourceName getApiSourceName();

}

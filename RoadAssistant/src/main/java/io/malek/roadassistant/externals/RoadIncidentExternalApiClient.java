package io.malek.roadassistant.externals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RoadIncidentExternalApiClient<T> {

    Page<T> getRoadIncidents(LocalDate incidentTime, Pageable pageable);

    ApiSourceName getApiSourceName();

}

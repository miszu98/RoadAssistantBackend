package io.malek.roadassistant.refreshers;

import io.malek.roadassistant.externals.RoadIncidentExternalApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
class RoadIncidentApiRefresher {

    private final Set<RoadIncidentExternalApiClient<?>> roadIncidentExternalApiClients;

    void refreshRoadIncidents(LocalDate incidentTime, Pageable pageable) {
        roadIncidentExternalApiClients.forEach(
                client -> client.getRoadIncidents(incidentTime, pageable)
        );
    }

}

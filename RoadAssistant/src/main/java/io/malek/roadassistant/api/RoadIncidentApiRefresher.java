package io.malek.roadassistant.api;

import io.malek.RoadIncident;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class RoadIncidentApiRefresher implements RoadIncidentApi<Set<ExternalApiResponse<RoadIncident>>> {

    private final Set<RoadIncidentExternalApiClient<RoadIncident>> roadIncidentExternalApiClients;

    @Override
    public Set<ExternalApiResponse<RoadIncident>> refreshRoadIncidents(LocalDate incidentTime, Pageable pageable) {
        return roadIncidentExternalApiClients.stream()
                .map(client -> ExternalApiResponse.of(client.getApiSourceName(), client.getRoadIncidents(incidentTime, pageable)))
                .collect(Collectors.toSet());
    }

}

package io.malek.roadassistant.api;

import io.malek.RoadIncident;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
class RoadIncidentApiRefresher implements RoadIncidentApi<Set<ExternalApiResponse<RoadIncident>>> {

    private final Set<RoadIncidentExternalApiClient<RoadIncident>> roadIncidentExternalApiClients;

    @Override
    public Set<ExternalApiResponse<RoadIncident>> refreshRoadIncidents(LocalDate incidentTime) {
        return roadIncidentExternalApiClients.stream()
                .map(client -> ExternalApiResponse.of(client.getApiSourceName(), client.getRoadIncidents(incidentTime)))
                .collect(Collectors.toSet());
    }

}

package io.malek.roadassistant.refreshers;

import io.malek.roadassistant.externals.ExternalApiResponse;
import io.malek.roadassistant.externals.RoadIncidentExternalApiClient;
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
public class RoadIncidentApiRefresher {

    private final Set<RoadIncidentExternalApiClient<?>> roadIncidentExternalApiClients;

    public Set<ExternalApiResponse<?>> refreshRoadIncidents(LocalDate incidentTime, Pageable pageable) {
        return roadIncidentExternalApiClients.stream()
                .map(client -> ExternalApiResponse.of(client.getApiSourceName(), client.getRoadIncidents(incidentTime, pageable)))
                .collect(Collectors.toSet());
    }

}

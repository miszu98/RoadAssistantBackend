package io.malek.roadassistant.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.RoadIncident;
import io.malek.roadassistant.websockets.WebSocketCommunicationService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
class DefaultRoadIncidentFacade implements RoadIncidentFacade {
    private final ObjectMapper objectMapper;
    private final RoadIncidentApi<Set<ExternalApiResponse<RoadIncident>>> roadIncidentApi;
    private final WebSocketCommunicationService<String> webSocketCommunicationService;

    public void broadcastLatestRoadIncidents() {
        Set<String> roadIncidentsJsons = loadRoadIncidents(LocalDate.now(), Pageable.unpaged());
        if (webSocketCommunicationService.clientIsConnected()) {
            log.info("Client is connected to webSocket, trying to send information about latest road incidents");
            roadIncidentsJsons.forEach(webSocketCommunicationService::sendMessage);
            return;
        } log.warn("Client is not connected, skip broadcasting latest road incidents");
    }

    private Set<String> loadRoadIncidents(LocalDate incidentTime, Pageable pageable) {
        Set<ExternalApiResponse<RoadIncident>> externalApiResponses = roadIncidentApi.refreshRoadIncidents(incidentTime, pageable);
        return externalApiResponses.stream().map(externalApiResponse -> convertRoadIncidentToJson(externalApiResponse.roadIncidents()))
                .collect(Collectors.toSet());
    }

    private String convertRoadIncidentToJson(Object object) {
        return Try.of(() -> objectMapper.writeValueAsString(object))
                .onFailure(this::handleFailureConversion).getOrElseThrow(
                        () -> new RuntimeException(String.format("Failure while conversion: [%s]", object))
                );
    }

    private void handleFailureConversion(Throwable ex) {
        log.error("Road Incident conversion failure: [{}]", ex.getMessage());
    }

}

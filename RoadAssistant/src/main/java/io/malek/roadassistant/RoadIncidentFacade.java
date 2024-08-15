package io.malek.roadassistant;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.roadassistant.externals.ExternalApiResponse;
import io.malek.roadassistant.refreshers.RoadIncidentApiRefresher;
import io.malek.roadassistant.service.WebSocketCommunicationService;
import io.malek.roadassistant.service.WebSocketJsonCommunicationService;
import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;


@Slf4j
@Component
@RequiredArgsConstructor
public class RoadIncidentFacade implements WebSocketSessionLoader {
    private WebSocketCommunicationService<String> webSocketCommunicationService;
    private final ObjectMapper objectMapper;
    private final RoadIncidentApiRefresher roadIncidentApiRefresher;

    public void broadcastLatestRoadIncidents() {
        Set<String> roadIncidentsJsons = loadRoadIncidents(LocalDate.now(), Pageable.unpaged());
        if (nonNull(webSocketCommunicationService)) {
            log.info("Client is connected to webSocket, trying to send information about latest road incidents");
            roadIncidentsJsons.forEach(roadIncidentJson -> webSocketCommunicationService.sendMessage(roadIncidentJson));
            return;
        } log.warn("Client is not connected, skip broadcasting latest road incidents");
    }

    @Override
    public void loadSession(WebSocketSession session) {
        webSocketCommunicationService = new WebSocketJsonCommunicationService(session);
    }

    @Override
    public void removeSession() {
        webSocketCommunicationService = null;
    }

    private Set<String> loadRoadIncidents(LocalDate incidentTime, Pageable pageable) {
        Set<ExternalApiResponse<?>> externalApiResponses = roadIncidentApiRefresher.refreshRoadIncidents(incidentTime, pageable);
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

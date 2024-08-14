package io.malek.roadassistant.schedulers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.RoadIncident;
import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import io.malek.roadassistant.externals.RoadIncidentExternalApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDate;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoadIncidentRefreshScheduler implements WebSocketSessionLoader {

    private final RoadIncidentExternalApiClient<RoadIncident> roadIncidentExternalApiClient;
    private WebSocketSession webSocketSession;
    private final ObjectMapper objectMapper;

    @Scheduled(cron = "*/30 * * * * *")
    void refreshRoadIncident() {
        if (nonNull(webSocketSession)) {
            log.info("Starting refresh of road incidents");
            Page<RoadIncident> roadIncidents = roadIncidentExternalApiClient.getRoadIncidents(LocalDate.parse("2024-08-12"), Pageable.unpaged());
//        messagingTemplate.convertAndSend("/topic/data", roadIncidents);
            roadIncidents.forEach(el -> {
            try {
//                WebSocketConfig.WebSocketHandler.ping(el);
                webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(el)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            });
//        WebSocketConfig.WebSocketHandler.ping();ping
            log.info("Ending refresh");
        } else {
            log.info("Not connected client");
        }

    }

    @Override
    public void loadSession(WebSocketSession session) {
        this.webSocketSession = session;
    }

    @Override
    public void removeSession() {
        this.webSocketSession = null;
    }

}

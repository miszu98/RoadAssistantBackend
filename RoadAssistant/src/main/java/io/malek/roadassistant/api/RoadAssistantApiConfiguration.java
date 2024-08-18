package io.malek.roadassistant.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.RoadIncident;
import io.malek.roadassistant.websockets.WebSocketCommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
class RoadAssistantApiConfiguration {
    private final ObjectMapper objectMapper;
    private final RoadAssistantExternalApiClient roadAssistantExternalApiClient;
    private final WebSocketCommunicationService<String> webSocketCommunicationService;

    @Bean
    RoadIncidentFacade roadIncidentFacade() {
        return new DefaultRoadIncidentFacade(objectMapper, roadIncidentApi(), webSocketCommunicationService);
    }

    @Bean
    RoadIncidentApi<Set<ExternalApiResponse<RoadIncident>>> roadIncidentApi() {
        return new RoadIncidentApiRefresher(roadIncidentExternalApiClient());
    }

    @Bean
    Set<RoadIncidentExternalApiClient<RoadIncident>> roadIncidentExternalApiClient() {
        return Set.of(
                roadAssistantExternalApiClient
        );
    }

}

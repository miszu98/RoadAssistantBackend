package io.malek.roadassistant.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.Latitude;
import io.malek.Longitude;
import io.malek.RoadIncident;
import io.malek.roadassistant.websockets.WebSocketCommunicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultRoadIncidentFacadeTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    RoadIncidentApi<Set<ExternalApiResponse<RoadIncident>>> roadIncidentApi;

    @Mock
    WebSocketCommunicationService<String> webSocketCommunicationService;

    @InjectMocks
    DefaultRoadIncidentFacade defaultRoadIncidentFacade;

    @Test
    void shouldNotBroadcastRoadIncidentsWhenClientIsNotConnected() {
        defaultRoadIncidentFacade.broadcastLatestRoadIncidents();
        verifyNoInteractions(roadIncidentApi);
    }

    @Test
    void shouldBroadcastRoadIncidentsToWebSocketWhenClientIsConnected() {
        List<RoadIncident> roadIncidents = new ArrayList<>();
        roadIncidents.add(RoadIncident.now(Longitude.of(10d), Latitude.of(20d)));
        roadIncidents.add(RoadIncident.now(Longitude.of(23d), Latitude.of(9d)));
        PageImpl<RoadIncident> page = new PageImpl<>(roadIncidents);
        ExternalApiResponse<RoadIncident> roadIncidentExternalApiResponse = ExternalApiResponse.of(ApiSourceName.ROAD_ASSISTANT_DATA_GENERATOR, page);

        when(webSocketCommunicationService.clientIsConnected()).thenReturn(true);
        when(roadIncidentApi.refreshRoadIncidents(any(), any()))
                .thenReturn(Set.of(roadIncidentExternalApiResponse));

        defaultRoadIncidentFacade.broadcastLatestRoadIncidents();

        verify(roadIncidentApi).refreshRoadIncidents(any(), any());
    }
}
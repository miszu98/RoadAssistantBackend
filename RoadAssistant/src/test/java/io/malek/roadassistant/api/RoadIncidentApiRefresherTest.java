package io.malek.roadassistant.api;

import io.malek.Latitude;
import io.malek.Longitude;
import io.malek.RoadIncident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;

import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static io.malek.roadassistant.api.RoadIncidentApiResponseAssert.*;

@ExtendWith(MockitoExtension.class)
class RoadIncidentApiRefresherTest {

    @Mock
    RoadIncidentExternalApiClient<RoadIncident> roadIncidentRoadIncidentExternalApiClient;

    @Mock
    Set<RoadIncidentExternalApiClient<RoadIncident>> roadIncidentExternalApiClientSet;

    @InjectMocks
    RoadIncidentApiRefresher roadIncidentApiRefresher;

    @Test
    void shouldReturnMappedRoadIncidentsFromExternalApi() {
        final LocalDate incidentTime = LocalDate.parse("2024-04-01");
        final Pageable pageable = Pageable.unpaged();

        when(roadIncidentExternalApiClientSet.stream())
                .thenReturn(Stream.of(roadIncidentRoadIncidentExternalApiClient));
        when(roadIncidentRoadIncidentExternalApiClient.getRoadIncidents(incidentTime, pageable)).thenReturn(getMockApiResponse());
        when(roadIncidentRoadIncidentExternalApiClient.getApiSourceName())
                .thenReturn(ApiSourceName.ROAD_ASSISTANT_DATA_GENERATOR);

        Set<ExternalApiResponse<RoadIncident>> externalApiResponses = roadIncidentApiRefresher.refreshRoadIncidents(incidentTime, pageable);
        assertThatRoadIncidentApiResponse(externalApiResponses).isFullMappedApiResponse();
    }

    private Page<RoadIncident> getMockApiResponse() {
        Set<RoadIncident> content = Collections.singleton(
                RoadIncident.now(Longitude.of(33d), Latitude.of(12d)));
        return new PageImpl<>(content.stream().toList(), Pageable.unpaged(), content.size());
    }

}
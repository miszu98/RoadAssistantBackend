package io.malek.roadassistant.api;

import io.malek.RoadIncident;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor(staticName = "assertThatRoadIncidentApiResponse")
class RoadIncidentApiResponseAssert {

    private final Set<ExternalApiResponse<RoadIncident>> externalApiResponses;

    RoadIncidentApiResponseAssert isFullMappedApiResponse() {
        assertEquals(1, externalApiResponses.size());
        ExternalApiResponse<RoadIncident> externalApiResponse = externalApiResponses.iterator().next();
        List<RoadIncident> content = externalApiResponse.roadIncidents().getContent();
        assertEquals(externalApiResponse.apiSourceName(), ApiSourceName.ROAD_ASSISTANT_DATA_GENERATOR);
        assertEquals(1, content.size());
        assertEquals(12d, content.get(0).latitude().value());
        assertEquals(33d, content.get(0).longitude().value());
        assertNotNull(content.get(0).incidentTime());
        return this;
    }

}

package io.malek.roadassistant.api;

import io.malek.RoadIncident;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient("${externals.api.roadAssistantDataGenerator.name}")
interface RoadAssistantExternalApiClient extends RoadIncidentExternalApiClient<RoadIncident> {

    @GetMapping("${externals.api.roadAssistantDataGenerator.fetchEndpoint}")
    List<RoadIncident> getRoadIncidents(@RequestParam LocalDate incidentTime);

    @Override
    default ApiSourceName getApiSourceName() {
        return ApiSourceName.ROAD_ASSISTANT_DATA_GENERATOR;
    }

}

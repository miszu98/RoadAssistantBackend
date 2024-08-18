package io.malek.roadassistant.api;

import io.malek.RoadIncident;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient("${externals.api.roadAssistantDataGenerator.name}")
interface RoadAssistantExternalApiClient extends RoadIncidentExternalApiClient<RoadIncident> {

    @GetMapping("${externals.api.roadAssistantDataGenerator.fetchEndpoint}")
    Page<RoadIncident> getRoadIncidents(@RequestParam LocalDate incidentTime, @RequestParam Pageable pageable);

    @Override
    default ApiSourceName getApiSourceName() {
        return ApiSourceName.ROAD_ASSISTANT_DATA_GENERATOR;
    }

}

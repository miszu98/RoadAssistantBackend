package io.malek.roadassistant.schedulers;

import io.malek.roadassistant.api.RoadIncidentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
class RoadIncidentRefreshScheduler {
    private final RoadIncidentFacade roadIncidentFacade;

    @Scheduled(cron = "*/30 * * * * *")
    void refreshRoadIncident() {
        log.info("Starting job for broadcasting latest road incidents");
        roadIncidentFacade.broadcastLatestRoadIncidents();
        log.info("Finished broadcasting latest road incidents job");
    }

}

package io.malek.roadassistant.schedulers;

import io.malek.roadassistant.RoadIncidentFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoadIncidentRefreshScheduler {
    private final RoadIncidentFacade roadIncidentFacade;

    @Scheduled(cron = "*/30 * * * * *")
    void refreshRoadIncident() {
        log.info("Starting job for broadcasting latest road incidents");
        roadIncidentFacade.broadcastLatestRoadIncidents();
        log.info("Finished broadcasting latest road incidents job");
    }

}

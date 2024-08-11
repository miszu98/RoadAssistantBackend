package io.malek.roadassistantdatagenerator.incidents;

import io.malek.RoadIncident;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
class RoadIncidentScheduler {
    private final RoadIncidentFacade roadIncidentFacade;
    private final Random random = new Random();

    @Scheduled(cron = "0 * * * * ?")
    void generateRoadIncident() {
        log.info("Starting task with generate road incident");
        int randomDelay = 1 + random.nextInt(5);
        RoadIncident roadIncident = roadIncidentFacade.generateIncident();
        Try.run(() -> threadSleep(randomDelay))
                .onFailure(e -> log.error("Exception thrown: {}", e.getMessage()))
                .onSuccess(e -> log.info("Successfully generated incident: [{}, {}]", roadIncident.latitude(), roadIncident.longitude()));
    }

    void threadSleep(int minuteDelay) throws InterruptedException {
        log.info("Wait: [{}] minutes", minuteDelay);
        Thread.sleep((long) minuteDelay * 60 * 1000);
    }

}

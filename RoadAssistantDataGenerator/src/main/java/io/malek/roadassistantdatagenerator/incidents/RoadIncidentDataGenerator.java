package io.malek.roadassistantdatagenerator.incidents;

import io.malek.Latitude;
import io.malek.Longitude;
import io.malek.RoadIncident;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class RoadIncidentDataGenerator {

    static RoadIncident generateIncidentCords() {
        log.info("Generating latitude and longitude coordinates");
        double latitude = Math.random();
        double longitude = Math.random();
        return RoadIncident.now(Longitude.of(longitude), Latitude.of(latitude));
    }

}

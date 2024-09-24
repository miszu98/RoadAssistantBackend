package io.malek.roadassistantauthorization.general.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class ClockConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemDefaultZone();
    }

}

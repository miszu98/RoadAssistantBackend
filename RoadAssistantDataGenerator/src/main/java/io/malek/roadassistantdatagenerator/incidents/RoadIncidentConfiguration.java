package io.malek.roadassistantdatagenerator.incidents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class RoadIncidentConfiguration {
    private final RoadIncidentRepository roadIncidentRepository;

    @Bean
    RoadIncidentFacade roadIncidentFacade() {
        return new RoadIncidentFacade(roadIncidentRepository, objectMapper());
    }

    @Bean
    RoadIncidentScheduler roadIncidentScheduler() {
        return new RoadIncidentScheduler(roadIncidentFacade());
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}

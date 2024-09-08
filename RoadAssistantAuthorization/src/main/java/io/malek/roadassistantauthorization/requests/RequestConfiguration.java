package io.malek.roadassistantauthorization.requests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RequestConfiguration {

    @Bean
    RegisterRequestAspect registerRequestAspect() {
        return new RegisterRequestAspect();
    }

}

package io.malek.roadassistantauthorization.requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@RequiredArgsConstructor
class RequestConfiguration {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final ObjectMapper objectMapper;
    private final Clock clock;

    @Bean
    RegisterRequestAspect registerRequestAspect() {
        return new RegisterRequestAspect(requestRegisterFacade());
    }

    @Bean
    RequestRegisterFacade requestRegisterFacade() {
        return new DefaultRequestRegisterFacade(requestRepository, requestMapper, objectMapper, clock);
    }

}

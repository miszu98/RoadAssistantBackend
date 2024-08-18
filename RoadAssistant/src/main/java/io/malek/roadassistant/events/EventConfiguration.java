package io.malek.roadassistant.events;

import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class EventConfiguration {
    private final WebSocketSessionLoader webSocketSessionLoader;

    @Bean
    ClientDisconnectedHandler clientDisconnectedHandler() {
        return new ClientDisconnectedHandler(webSocketSessionLoader);
    }

    @Bean
    ClientConnectedHandler clientConnectedHandler() {
        return new ClientConnectedHandler(webSocketSessionLoader);
    }
}

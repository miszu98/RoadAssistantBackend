package io.malek.roadassistant.road_incidents;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ClientDisconnectedHandler {

    private final WebSocketSessionLoader webSocketSessionLoader;

    @EventListener(ClientDisconnectedEvent.class)
    public void handleClientDisconnectedEvent() {
        log.info("Remove websocket session");
        webSocketSessionLoader.removeSession();
    }

}

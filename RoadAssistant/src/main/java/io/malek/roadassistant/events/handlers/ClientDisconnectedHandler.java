package io.malek.roadassistant.events.handlers;

import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import io.malek.roadassistant.events.ClientDisconnectedEvent;
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

package io.malek.roadassistant.events;

import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
class ClientDisconnectedHandler {

    private final WebSocketSessionLoader webSocketSessionLoader;

    @EventListener(ClientDisconnectedEvent.class)
    public void handleClientDisconnectedEvent() {
        log.info("Remove websocket session");
        webSocketSessionLoader.removeSession();
    }

}

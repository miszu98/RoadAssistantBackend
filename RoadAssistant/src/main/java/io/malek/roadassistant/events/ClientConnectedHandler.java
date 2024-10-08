package io.malek.roadassistant.events;

import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RequiredArgsConstructor
class ClientConnectedHandler {
    private final WebSocketSessionLoader webSocketSessionLoader;

    @EventListener(ClientConnectedEvent.class)
    public void handleClientConnectedEvent(ClientConnectedEvent event) {
        WebSocketSession webSocketSession = event.getSession();
        log.info("Load websocket session: [{}]", webSocketSession.getRemoteAddress());
        webSocketSessionLoader.loadSession(webSocketSession);
    }

}

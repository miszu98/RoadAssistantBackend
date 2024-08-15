package io.malek.roadassistant.road_incidents;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
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

package io.malek.roadassistant.websockets;


import io.malek.roadassistant.events.ClientConnectedEvent;
import io.malek.roadassistant.events.ClientDisconnectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@RequiredArgsConstructor
class WebSocketHandler extends TextWebSocketHandler {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("Connection established: [{}]", session.getRemoteAddress());
        eventPublisher.publishEvent(new ClientConnectedEvent(this, session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Connection closed: [{}]", session.getRemoteAddress());
        eventPublisher.publishEvent(new ClientDisconnectedEvent(this));
    }

}

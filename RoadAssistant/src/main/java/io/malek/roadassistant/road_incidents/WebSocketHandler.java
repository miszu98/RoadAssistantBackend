package io.malek.roadassistant.road_incidents;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@Component
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

package io.malek.roadassistant.websockets;

import io.malek.roadassistant.events.ClientConnectedEvent;
import io.malek.roadassistant.events.ClientDisconnectedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WebSocketHandlerTest {

    @Mock
    ApplicationEventPublisher eventPublisher;

    @Mock
    WebSocketSession webSocketSession;

    @InjectMocks
    WebSocketHandler webSocketHandler;

    @Test
    void shouldPublishEventWhenClientConnecting() {
        webSocketHandler.afterConnectionEstablished(webSocketSession);
        verify(eventPublisher).publishEvent(any(ClientConnectedEvent.class));
    }

    @Test
    void shouldPublishEventWhenClientDisconnecting() {
        webSocketHandler.afterConnectionClosed(webSocketSession, CloseStatus.BAD_DATA);
        verify(eventPublisher).publishEvent(any(ClientDisconnectedEvent.class));
    }

}

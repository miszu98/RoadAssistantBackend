package io.malek.roadassistant.events;

import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientConnectedHandlerTest {

    @Mock
    WebSocketSessionLoader webSocketSessionLoader;

    @Mock
    WebSocketSession webSocketSession;

    @InjectMocks
    ClientConnectedHandler clientConnectedHandler;

    @Test
    void shouldLoadWebSocketSessionWhenDetectClientConnectedEvent() {
        ClientConnectedEvent clientConnectedEvent = new ClientConnectedEvent(this, webSocketSession);
        clientConnectedHandler.handleClientConnectedEvent(clientConnectedEvent);
        verify(webSocketSessionLoader).loadSession(webSocketSession);
    }

}

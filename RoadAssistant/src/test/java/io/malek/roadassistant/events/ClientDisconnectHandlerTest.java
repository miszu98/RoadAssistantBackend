package io.malek.roadassistant.events;

import io.malek.roadassistant.websockets.WebSocketSessionLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientDisconnectHandlerTest {

    @Mock
    WebSocketSessionLoader webSocketSessionLoader;

    @InjectMocks
    ClientDisconnectedHandler clientDisconnectedHandler;

    @Test
    void shouldLoadWebSocketSessionWhenDetectClientDisconnectEvent() {
        clientDisconnectedHandler.handleClientDisconnectedEvent();
        verify(webSocketSessionLoader).removeSession();
    }

}

package io.malek.roadassistant.websockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WebSocketCommunicationServiceTest {

    @Mock
    WebSocketSession webSocketSession;

    @InjectMocks
    WebSocketJsonCommunicationService webSocketJsonCommunicationService;

    @Test
    void shouldThrowExceptionWhenWebSocketSessionIsUnavailable() {
        final String testMessage = "test message";
        webSocketJsonCommunicationService.removeSession();

        assertThrows(WebSocketCommunicationException.class,
                () -> webSocketJsonCommunicationService.sendMessage(testMessage));
    }

    @Test
    void shouldNotThrowExceptionWhenWebSocketSessionIsAvailable() {
        final String testMessage = "test message";
        webSocketJsonCommunicationService.loadSession(webSocketSession);

        assertDoesNotThrow(() -> webSocketJsonCommunicationService.sendMessage(testMessage));
    }

    @Test
    void shouldReturnTrueWhenWebSocketSessionIsAvailable() {
        webSocketJsonCommunicationService.loadSession(webSocketSession);
        assertTrue(webSocketJsonCommunicationService.clientIsConnected());
    }

}

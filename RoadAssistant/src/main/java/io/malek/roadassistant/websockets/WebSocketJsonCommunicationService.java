package io.malek.roadassistant.websockets;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static java.util.Objects.nonNull;

@Slf4j
@Service
class WebSocketJsonCommunicationService implements WebSocketCommunicationService<String> {
    private WebSocketSession webSocketSession;

    @Override
    public void sendMessage(String message) {
        log.info("Trying to send message to websocket: [{}]", message);
        Try.run(() -> webSocketSession.sendMessage(new TextMessage(message)))
                .onSuccess(result -> handleSuccessCommunicationWithWebSocket())
                .onFailure(this::handleFailureCommunicationWithWebSocket);
    }

    @Override
    public void loadSession(WebSocketSession session) {
        this.webSocketSession = session;
    }

    @Override
    public void removeSession() {
        this.webSocketSession = null;
    }

    @Override
    public boolean clientIsConnected() {
        return nonNull(webSocketSession);
    }

    private void handleSuccessCommunicationWithWebSocket() {
        log.info("Sending message to websocket was successful");
    }

    private void handleFailureCommunicationWithWebSocket(Throwable exception) {
        log.error("Sending message to websocket was failure");
        throw new WebSocketCommunicationException(exception.getMessage());
    }

}

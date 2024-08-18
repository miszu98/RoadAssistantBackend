package io.malek.roadassistant;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@RequiredArgsConstructor
class WebSocketJsonCommunicationService implements WebSocketCommunicationService<String> {
    private final WebSocketSession webSocketSession;

    @Override
    public void sendMessage(String message) {
        log.info("Trying to send message to websocket: [{}]", message);
        Try.run(() -> webSocketSession.sendMessage(new TextMessage(message)))
                .onSuccess(result -> handleSuccessCommunicationWithWebSocket())
                .onFailure(this::handleFailureCommunicationWithWebSocket);
    }

    private void handleSuccessCommunicationWithWebSocket() {
        log.info("Sending message to websocket was successful");
    }

    private void handleFailureCommunicationWithWebSocket(Throwable exception) {
        log.error("Sending message to websocket was failure");
        throw new WebSocketCommunicationException(exception.getMessage());
    }

}

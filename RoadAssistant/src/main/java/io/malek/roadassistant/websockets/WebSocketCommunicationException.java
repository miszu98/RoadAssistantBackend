package io.malek.roadassistant.websockets;

import lombok.Getter;

@Getter
class WebSocketCommunicationException extends RuntimeException {
    public WebSocketCommunicationException(String message) {
        super(message);
    }
}

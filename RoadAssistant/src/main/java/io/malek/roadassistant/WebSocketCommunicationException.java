package io.malek.roadassistant;

import lombok.Getter;

@Getter
class WebSocketCommunicationException extends RuntimeException {
    public WebSocketCommunicationException(String message) {
        super(message);
    }
}

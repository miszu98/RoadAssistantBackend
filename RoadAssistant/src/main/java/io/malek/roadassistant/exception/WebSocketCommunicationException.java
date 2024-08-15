package io.malek.roadassistant.exception;

import lombok.Getter;

@Getter
public class WebSocketCommunicationException extends RuntimeException {
    public WebSocketCommunicationException(String message) {
        super(message);
    }
}

package io.malek.roadassistant.road_incidents;

import lombok.Getter;

@Getter
class WebSocketCommunicationException extends RuntimeException {
    public WebSocketCommunicationException(String message) {
        super(message);
    }
}

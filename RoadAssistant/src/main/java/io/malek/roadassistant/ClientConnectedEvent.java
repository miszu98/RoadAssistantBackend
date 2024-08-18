package io.malek.roadassistant;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.socket.WebSocketSession;

@Getter
class ClientConnectedEvent extends ApplicationEvent {
    private WebSocketSession session;

    public ClientConnectedEvent(Object source, WebSocketSession session) {
        super(source);
        this.session = session;
    }

}

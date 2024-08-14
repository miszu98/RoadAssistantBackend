package io.malek.roadassistant.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.socket.WebSocketSession;

@Getter
public class ClientConnectedEvent extends ApplicationEvent {
    private WebSocketSession session;

    public ClientConnectedEvent(Object source, WebSocketSession session) {
        super(source);
        this.session = session;
    }

}

package io.malek.roadassistant.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ClientDisconnectedEvent extends ApplicationEvent {

    public ClientDisconnectedEvent(Object source) {
        super(source);
    }

}

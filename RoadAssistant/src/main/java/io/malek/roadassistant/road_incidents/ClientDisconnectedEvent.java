package io.malek.roadassistant.road_incidents;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
class ClientDisconnectedEvent extends ApplicationEvent {

    public ClientDisconnectedEvent(Object source) {
        super(source);
    }

}

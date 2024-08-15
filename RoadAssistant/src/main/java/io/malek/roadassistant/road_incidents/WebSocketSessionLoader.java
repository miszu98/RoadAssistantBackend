package io.malek.roadassistant.road_incidents;

import org.springframework.web.socket.WebSocketSession;

interface WebSocketSessionLoader {

    void loadSession(WebSocketSession session);
    void removeSession();


}

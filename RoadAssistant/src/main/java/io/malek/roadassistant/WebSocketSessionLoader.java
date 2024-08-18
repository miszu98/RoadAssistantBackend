package io.malek.roadassistant;

import org.springframework.web.socket.WebSocketSession;

interface WebSocketSessionLoader {

    void loadSession(WebSocketSession session);
    void removeSession();


}

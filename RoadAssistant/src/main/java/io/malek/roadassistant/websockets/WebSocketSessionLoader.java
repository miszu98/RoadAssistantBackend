package io.malek.roadassistant.websockets;

import org.springframework.web.socket.WebSocketSession;

public interface WebSocketSessionLoader {

    void loadSession(WebSocketSession session);
    void removeSession();
    boolean clientIsConnected();

}

package io.malek.roadassistant.websockets;


public interface WebSocketCommunicationService<T> extends WebSocketSessionLoader {

    void sendMessage(T message);

}

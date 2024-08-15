package io.malek.roadassistant.service;

public interface WebSocketCommunicationService<T> {

    void sendMessage(T message);

}

package io.malek.roadassistant;

interface WebSocketCommunicationService<T> {

    void sendMessage(T message);

}

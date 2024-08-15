package io.malek.roadassistant.road_incidents;

interface WebSocketCommunicationService<T> {

    void sendMessage(T message);

}

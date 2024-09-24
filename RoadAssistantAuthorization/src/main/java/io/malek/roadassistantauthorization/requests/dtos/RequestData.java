package io.malek.roadassistantauthorization.requests.dtos;


public record RequestData(String value) {

    public static RequestData of(String value) {
        return new RequestData(value);
    }

}

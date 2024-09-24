package io.malek.roadassistantauthorization.requests.dtos;

public record ResponseData(String value) {

    public static ResponseData of(String value) {
        return new ResponseData(value);
    }

}

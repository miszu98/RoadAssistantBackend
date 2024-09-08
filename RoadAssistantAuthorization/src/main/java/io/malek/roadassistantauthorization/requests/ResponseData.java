package io.malek.roadassistantauthorization.requests;

record ResponseData(String value) {

    static ResponseData of(String value) {
        return new ResponseData(value);
    }

}

package io.malek.roadassistantauthorization.requests;


record RequestData(String value) {

    static RequestData of(String value) {
        return new RequestData(value);
    }

}

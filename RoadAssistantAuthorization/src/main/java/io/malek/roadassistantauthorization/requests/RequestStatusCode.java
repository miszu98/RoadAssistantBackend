package io.malek.roadassistantauthorization.requests;

import io.vavr.control.Try;
import org.springframework.http.HttpStatusCode;

record RequestStatusCode(int value) {

    static RequestStatusCode of(int value) {
        HttpStatusCode httpStatusCode = Try.of(() -> HttpStatusCode.valueOf(value))
                .getOrElseThrow(() -> new IllegalArgumentException(String.format("Unrecognized Http status code: %s", value)));
        return new RequestStatusCode(httpStatusCode.value());
    }

}

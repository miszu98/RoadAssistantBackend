package io.malek.roadassistantauthorization.requests.dtos;

import io.vavr.control.Try;
import org.springframework.http.HttpStatusCode;

public record RequestStatusCode(int value) {

    public RequestStatusCode {
        Try.run(() -> HttpStatusCode.valueOf(value))
                .getOrElseThrow(() -> new IllegalArgumentException(String.format("Unrecognized Http status code: %s", value)));
    }

    public static RequestStatusCode of(int value) {
        return new RequestStatusCode(value);
    }

}

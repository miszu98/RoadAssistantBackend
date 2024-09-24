package io.malek.roadassistantauthorization.user.register;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

record ExceptionResponse(LocalDateTime errorTime, List<String> messages) {

    ExceptionResponse {
        if (isNull(errorTime) || isNull(messages)) {
            throw new IllegalArgumentException("Fields errorTime and messages cannot be null");
        }
    }

    static ExceptionResponse of(LocalDateTime errorTime, List<String> messages) {
        return new ExceptionResponse(errorTime, messages);
    }

}

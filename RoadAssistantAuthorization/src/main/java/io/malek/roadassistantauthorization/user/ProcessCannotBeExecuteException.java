package io.malek.roadassistantauthorization.user;

import lombok.Getter;

@Getter
public class ProcessCannotBeExecuteException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "This request should has unique processId";

    public ProcessCannotBeExecuteException(String message) {
        super(message);
    }

    public ProcessCannotBeExecuteException() {
        super(DEFAULT_MESSAGE);
    }
}

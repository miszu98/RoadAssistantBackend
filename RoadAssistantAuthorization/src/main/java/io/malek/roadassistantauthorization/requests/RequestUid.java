package io.malek.roadassistantauthorization.requests;


import io.vavr.control.Try;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

record RequestUid(String value) {

    public RequestUid {
        boolean processIdIsBlankOrWrongFormat = isBlank(value) || requestUidIsBadFormat(value);
        if (processIdIsBlankOrWrongFormat) {
            throw new IllegalArgumentException("ProcessId cannot be null or empty string and should be UUID");
        }
    }

    public static RequestUid of(String value) {
        return new RequestUid(value);
    }

    public static RequestUid newOne() {
        return new RequestUid(UUID.randomUUID().toString());
    }

    public static RequestUid empty() {
        return new RequestUid(null);
    }

    private boolean requestUidIsBadFormat(String value) {
        return Try.of(() -> UUID.fromString(value)).isFailure();
    }

}

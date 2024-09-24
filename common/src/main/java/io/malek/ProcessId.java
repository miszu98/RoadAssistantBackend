package io.malek;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.vavr.control.Try;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record ProcessId(String value) {

    public ProcessId {
        boolean processIdIsBlankOrWrongFormat = isBlank(value) || processIdIsBadFormat(value);
        if (processIdIsBlankOrWrongFormat) {
            throw new IllegalArgumentException("ProcessId cannot be null or empty string and should be UUID");
        }
    }

    @JsonCreator
    public static ProcessId of(String value) {
        return new ProcessId(value);
    }

    public static ProcessId newOne() {
        return new ProcessId(UUID.randomUUID().toString());
    }

    public static ProcessId empty() {
        return new ProcessId(null);
    }

    private boolean processIdIsBadFormat(String value) {
        return Try.of(() -> UUID.fromString(value)).isFailure();
    }
}

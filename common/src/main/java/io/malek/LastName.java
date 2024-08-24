package io.malek;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record LastName(String value) {

    public LastName {
        if (isBlank(value)) {
            throw new IllegalArgumentException("LastName cannot be null or empty string");
        }
    }

    public static LastName of(String value) {
        return new LastName(value);
    }

}

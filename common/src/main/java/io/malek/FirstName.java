package io.malek;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record FirstName(String value) {

    public FirstName {
        if (isBlank(value)) {
            throw new IllegalArgumentException("FirstName cannot be null or empty string");
        }
    }

    public static FirstName of(String value) {
        return new FirstName(value);
    }

}

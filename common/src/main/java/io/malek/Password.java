package io.malek;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record Password(String value) {

    public Password {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Password cannot be null or empty string and should has correct format");
        }
    }

    @JsonCreator
    public static Password of(String value) {
        return new Password(value);
    }

    public int length() {
        return value.length();
    }

    public boolean contains(String element) {
        return value.contains(element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Password password = (Password) o;

        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}

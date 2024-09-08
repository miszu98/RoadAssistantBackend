package io.malek.roadassistantauthorization.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.apache.logging.log4j.util.Strings.isBlank;


public record Password(String value) {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Za-z])(?=.*[A-Z])[A-Za-z\\d!@#$%^&*]{8,16}$";
    private static final Pattern PASSWORD_REGEX_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public Password {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Password cannot be null or empty string and should has correct format");
        }
        validatePassword(value);
    }

    private void validatePassword(String value) {
        boolean isNotCorrectPassword = !PASSWORD_REGEX_PATTERN.matcher(value).matches();
        if (isNotCorrectPassword) {
            throw new IllegalArgumentException("Password should has min 8 and max 16 characters, at least one special char and number");
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

package io.malek.roadassistantauthorization.user.dtos;


import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.isBlank;


public record Email(String value) {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Email cannot be null or empty string");
        }
        validateEmail(value);
    }

    @JsonCreator
    public static Email of(String value) {
        return new Email(value);
    }

    private void validateEmail(String email) {
        boolean emailIsNotValid = !EMAIL_PATTERN.matcher(email).matches();
        if (emailIsNotValid) {
            throw new IllegalArgumentException("Email should have correct format");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;

        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}

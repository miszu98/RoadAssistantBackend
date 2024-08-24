package io.malek;


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

    public static Email of(String value) {
        return new Email(value);
    }

    private void validateEmail(String email) {
        boolean emailIsNotValid = !EMAIL_PATTERN.matcher(email).matches();
        if (emailIsNotValid) {
            throw new IllegalArgumentException("Email should have correct format");
        }
    }

}

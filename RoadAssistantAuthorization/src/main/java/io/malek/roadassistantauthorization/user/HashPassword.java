package io.malek.roadassistantauthorization.user;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record HashPassword(String value) {

    private static final String BCRYPT_REGEX = "^\\$2a\\$[0-9]{2}\\$[./a-zA-Z0-9]{53}$";
    private static final Pattern BCRYPT_PATTERN = Pattern.compile(BCRYPT_REGEX);

    public HashPassword {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Password cannot be null or empty string");
        }
        validatePassword(value);
    }

    public static HashPassword of(String value) {
        return new HashPassword(value);
    }

    private void validatePassword(String password) {
        boolean notHashedByBcrypt = !BCRYPT_PATTERN.matcher(password).matches();
        if (notHashedByBcrypt) {
            throw new IllegalArgumentException("Password should be hashed by BCRYPT, raw password is not acceptable");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashPassword password = (HashPassword) o;

        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

}

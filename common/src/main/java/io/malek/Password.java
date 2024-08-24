package io.malek;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

public record Password(String value) {
    private static final String BCRYPT_REGEX = "^\\$2a\\$[0-9]{2}\\$[./a-zA-Z0-9]{53}$";
    private static final Pattern BCRYPT_PATTERN = Pattern.compile(BCRYPT_REGEX);

    public Password {
        if (isBlank(value)) {
            throw new IllegalArgumentException("Password cannot be null or empty string");
        }
        validatePassword(value);
    }

    public static Password of(String value) {
        return new Password(value);
    }

    private void validatePassword(String password) {
        boolean notHashedByBcrypt = !BCRYPT_PATTERN.matcher(password).matches();
        if (notHashedByBcrypt) {
            throw new IllegalArgumentException("Password should be hashed by BCRYPT, raw password is not acceptable");
        }
    }

}

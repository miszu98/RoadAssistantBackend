package io.malek.roadassistantauthorization.user.enums;

import lombok.Getter;

@Getter
public enum ValidatorName {
    USER_FIRST_NAME_IS_NULL_VALIDATOR("User firstName field cannot be null"),
    USER_FIRST_NAME_LENGTH_VALIDATOR("User firstName validator"),

    USER_LAST_NAME_VALIDATOR("User lastName field cannot be null"),

    USER_EMAIL_IS_NULL_VALIDATOR("User email field cannot be null"),

    USER_PASSWORD_IS_NULL_VALIDATOR("User password field cannot be null"),

    USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR("User repeat password field cannot be null"),

    USER_PASSWORDS_EQUALITY_VALIDATOR("User passwords cannot be different"),

    USER_PASSWORD_LENGTH_VALIDATOR("Password should has min 8 and max 16 characters"),
    USER_PASSWORD_SPECIAL_CHARS_VALIDATOR("Password should has at least one special char: [.!@#$%^&*()/]"),
    USER_PASSWORD_NUMBER_VALIDATOR("Password should has at least one number"),

    EMAIL_FORMAT_VALIDATOR("Email should has correct format"),

    USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR("User creation processId cannot be null");

    private String message;

    ValidatorName(String message) {
        this.message = message;
    }
}

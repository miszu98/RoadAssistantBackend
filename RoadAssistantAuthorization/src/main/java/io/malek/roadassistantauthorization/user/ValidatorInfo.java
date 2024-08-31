package io.malek.roadassistantauthorization.user;


import static java.util.Objects.isNull;

public record ValidatorInfo(ValidatorName validatorName, ValidatorStatus status, String message) {

    public ValidatorInfo {
        if (isNull(validatorName) || isNull(status)) {
            throw new IllegalArgumentException("validatorName and status are required fields");
        }
    }

    public static ValidatorInfo of(ValidatorName validatorName, ValidatorStatus status) {
        return new ValidatorInfo(validatorName, status, status == ValidatorStatus.FAILED ? validatorName.getMessage() : null);
    }

}

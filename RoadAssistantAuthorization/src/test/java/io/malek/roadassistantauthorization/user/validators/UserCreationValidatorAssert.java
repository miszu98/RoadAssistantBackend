package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.dtos.ValidatorInfo;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.data_generators.UserCreationRequestDataGeneratorUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(staticName = "assertThatUserCreationValidation")
class UserCreationValidatorAssert {

    private final Set<ValidatorInfo> validatorInfos;

    UserCreationValidatorAssert isValidatorPassed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoAllPassed(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isValidatorFailed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoAllFailed(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isNullPasswordValidatorFailedAndSkipPasswordEqualityValidator() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoPasswordNullValidatorFailedAndSkipPasswordEqualityValidator(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isNullFirstnameValidatorFailed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoFirstNameNullValidatorFailed(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isNullLastnameValidatorFailed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoLastNameNullValidatorFailed(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isNullEmailValidatorFailed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoEmailNullValidatorFailed(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isNullPasswordRepeatValidatorFailed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoPasswordRepeatIsNullValidatorFailed(), validatorInfos);
        return this;
    }

    UserCreationValidatorAssert isPasswordEqualityValidatorFailed() {
        assertFalse(validatorInfos.isEmpty());
        assertEquals(getValidatorsInfoPasswordEqualityValidatorFailed(), validatorInfos);
        return this;
    }

}

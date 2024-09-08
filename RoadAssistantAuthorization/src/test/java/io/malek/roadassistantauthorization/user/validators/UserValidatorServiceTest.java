package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.ProcessCannotBeExecuteException;
import io.malek.roadassistantauthorization.user.UserCreationRequest;
import io.malek.roadassistantauthorization.user.ValidatorInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.data_generators.UserCreationRequestDataGeneratorUtil.*;
import static io.malek.roadassistantauthorization.user.validators.UserCreationValidatorAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserValidatorServiceTest {

    UserCreationValidatorService userCreationValidatorService;

    @BeforeEach
    void setUp() {
        userCreationValidatorService = new UserCreationValidatorService();
    }

    @Test
    void shouldPassedAllValidators() {
        final UserCreationRequest userCreationRequest = getCorrectUser();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isValidatorPassed();
    }

    @Test
    void shouldFailedAllValidators() {
        final UserCreationRequest userCreationRequest = getIncorrectUser();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isValidatorFailed();
    }

    @Test
    void shouldThrowExceptionWhenProcessIdIsNull() {
        final UserCreationRequest userCreationRequest = getUserCreationRequestWithoutProcessId();
        assertThrows(ProcessCannotBeExecuteException.class,
                () -> userCreationValidatorService.validateRequiredFields(userCreationRequest));
    }

    @Test
    void shouldFailedPasswordIsNullValidator() {
        final UserCreationRequest userCreationRequest = getUserWithNullPassword();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isNullPasswordValidatorFailedAndSkipPasswordEqualityValidator();
    }

    @Test
    void shouldFailedFirstNameIsNullValidator() {
        final UserCreationRequest userCreationRequest = getUserWithNullFirstName();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isNullFirstnameValidatorFailed();
    }

    @Test
    void shouldFailedLastNameIsNullValidator() {
        final UserCreationRequest userCreationRequest = getUserWithNullLastName();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isNullLastnameValidatorFailed();
    }

    @Test
    void shouldFailedEmailIsNullValidator() {
        final UserCreationRequest userCreationRequest = getUserWithNullEmail();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isNullEmailValidatorFailed();
    }


    @Test
    void shouldFailedPasswordRepeatIsNullValidator() {
        final UserCreationRequest userCreationRequest = getUserWithNullRepeatPassword();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isNullPasswordRepeatValidatorFailed();
    }

    @Test
    void shouldFailedPasswordEqualityValidatorWhenPasswordsAreDifferent() {
        final UserCreationRequest userCreationRequest = getUserWithDifferentPasswords();

        Set<ValidatorInfo> validatorInfos = userCreationValidatorService.validateRequiredFields(userCreationRequest);

        assertThatUserCreationValidation(validatorInfos).isPasswordEqualityValidatorFailed();
    }
}

package io.malek.roadassistantauthorization.user.data_generators;

import io.malek.roadassistantauthorization.user.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserCreationRequestDataGeneratorUtil {

    public static UserCreationRequest getCorrectUser() {
        return UserCreationRequest.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(LastName.of("Nowak"))
                .processId(ProcessId.newOne())
                .email(Email.of("roman.nowak@gmail.com"))
                .password(Password.of("gakljrghra1!A"))
                .repeatPassword(Password.of("gakljrghra1!A"))
                .build();
    }

    public static UserCreationRequest getUserWithDifferentPasswords() {
        return UserCreationRequest.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(LastName.of("Nowak"))
                .processId(ProcessId.newOne())
                .email(Email.of("roman.nowak@gmail.com"))
                .password(Password.of("gakljrghra1!A"))
                .repeatPassword(Password.of("gakljraaghra1!A"))
                .build();
    }

    public static UserCreationRequest getUserWithNullRepeatPassword() {
        return UserCreationRequest.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(LastName.of("Nowak"))
                .processId(ProcessId.newOne())
                .email(Email.of("roman.nowak@gmail.com"))
                .password(Password.of("gakljrghra1!A"))
                .repeatPassword(null)
                .build();
    }

    public static UserCreationRequest getUserWithNullEmail() {
        return UserCreationRequest.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(LastName.of("Nowak"))
                .processId(ProcessId.newOne())
                .email(null)
                .password(Password.of("gakljrghra1!A"))
                .repeatPassword(Password.of("gakljrghra1!A"))
                .build();
    }

    public static UserCreationRequest getUserWithNullLastName() {
        return UserCreationRequest.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(null)
                .processId(ProcessId.newOne())
                .email(Email.of("roman.nowak@gmail.com"))
                .password(Password.of("gakljrghra1!A"))
                .repeatPassword(Password.of("gakljrghra1!A"))
                .build();
    }

    public static UserCreationRequest getUserWithNullPassword() {
        return UserCreationRequest.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(LastName.of("Nowak"))
                .processId(ProcessId.newOne())
                .email(Email.of("roman.nowak@gmail.com"))
                .password(null)
                .repeatPassword(Password.of("gakljrghra1!A"))
                .build();
    }

    public static UserCreationRequest getUserWithNullFirstName() {
        return UserCreationRequest.builder()
                .firstName(null)
                .lastName(LastName.of("Nowak"))
                .processId(ProcessId.newOne())
                .email(Email.of("roman.nowak@gmail.com"))
                .password(Password.of("gakljrghra1!A"))
                .repeatPassword(Password.of("gakljrghra1!A"))
                .build();
    }


    public static UserCreationRequest getIncorrectUser() {
        return UserCreationRequest.builder().processId(ProcessId.newOne()).build();
    }

    public static UserCreationRequest getUserCreationRequestWithoutProcessId() {
        return UserCreationRequest.builder().build();
    }

    public static Set<ValidatorInfo> getValidatorsInfoAllPassed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.PASSED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoAllFailed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.SKIPPED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.FAILED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoPasswordNullValidatorFailedAndSkipPasswordEqualityValidator() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.SKIPPED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.PASSED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoFirstNameNullValidatorFailed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.FAILED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoLastNameNullValidatorFailed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.PASSED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoEmailNullValidatorFailed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.PASSED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoPasswordEqualityValidatorFailed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.PASSED)
        );
        return new HashSet<>(validatorInfos);
    }

    public static Set<ValidatorInfo> getValidatorsInfoPasswordRepeatIsNullValidatorFailed() {
        List<ValidatorInfo> validatorInfos = List.of(
                ValidatorInfo.of(ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_LAST_NAME_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR, ValidatorStatus.PASSED),
                ValidatorInfo.of(ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR, ValidatorStatus.FAILED),
                ValidatorInfo.of(ValidatorName.USER_FIRST_NAME_IS_NULL_VALIDATOR, ValidatorStatus.PASSED)
        );
        return new HashSet<>(validatorInfos);
    }

}

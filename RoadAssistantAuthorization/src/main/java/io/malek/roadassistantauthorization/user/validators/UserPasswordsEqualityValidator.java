package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.Password;
import io.malek.roadassistantauthorization.user.*;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.ValidatorName.USER_PASSWORDS_EQUALITY_VALIDATOR;

@Slf4j
class UserPasswordsEqualityValidator extends Validator<UserCreationRequest, ValidatorInfo, ValidatorName> {

    @Override
    public Set<ValidatorInfo> validate(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        log.info("Starting validation passwords equality of user with process id: [{}]", object.getProcessId().value());
        return Try.of(() -> validatePasswordsEquality(object, validatorInfos))
                .getOrElse(() -> {
                    validatorInfos.add(ValidatorInfo.of(getValidatorName(), ValidatorStatus.SKIPPED));
                    return validateNext(object, validatorInfos);
                });
    }

    private Set<ValidatorInfo> validatePasswordsEquality(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        final Password password = object.getPassword();
        final Password repeatPassword = object.getRepeatPassword();
        ValidatorName validatorName = getValidatorName();
        boolean passwordsAreNotTheSame = !password.equals(repeatPassword);
        if (passwordsAreNotTheSame) {
            log.error("User creation process with id: [{}] failed because password are not equal", object.getProcessId().value());
            validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.FAILED));
            return validateNext(object, validatorInfos);
        }
        validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.PASSED));
        return validateNext(object, validatorInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_PASSWORDS_EQUALITY_VALIDATOR;
    }

}
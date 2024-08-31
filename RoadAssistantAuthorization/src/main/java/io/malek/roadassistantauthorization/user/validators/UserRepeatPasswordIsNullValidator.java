package io.malek.roadassistantauthorization.user.validators;

import io.malek.Password;
import io.malek.roadassistantauthorization.user.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.ValidatorName.USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR;
import static java.util.Objects.isNull;

@Slf4j
class UserRepeatPasswordIsNullValidator extends Validator<UserCreationRequest, ValidatorInfo, ValidatorName> {

    @Override
    protected Set<ValidatorInfo> validate(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        log.info("Starting validation repeat password of user with process id: [{}]", object.getProcessId().value());
        final Password repeatePassword = object.getRepeatPassword();
        ValidatorName validatorName = getValidatorName();
        if (isNull(repeatePassword)) {
            log.error("User creation process with id: [{}] failed because repeat password is null", object.getProcessId().value());
            validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.FAILED));
            return validateNext(object, validatorInfos);
        }
        validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.PASSED));
        return validateNext(object, validatorInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_REPEAT_PASSWORD_IS_NULL_VALIDATOR;
    }

}
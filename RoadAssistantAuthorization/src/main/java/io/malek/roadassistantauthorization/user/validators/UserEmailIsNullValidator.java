package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.dtos.Email;
import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.ValidatorInfo;
import io.malek.roadassistantauthorization.user.enums.ValidatorName;
import io.malek.roadassistantauthorization.user.enums.ValidatorStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.enums.ValidatorName.USER_EMAIL_IS_NULL_VALIDATOR;
import static java.util.Objects.isNull;

@Slf4j
class UserEmailIsNullValidator extends Validator<UserCreationRequest, ValidatorInfo, ValidatorName> {

    @Override
    protected Set<ValidatorInfo> validate(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        log.info("Starting validation email of user with process id: [{}]", object.getProcessId().value());
        final Email email = object.getEmail();
        ValidatorName validatorName = getValidatorName();
        if (isNull(email)) {
            log.error("User creation process with id: [{}] failed because email is null", object.getProcessId().value());
            validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.FAILED));
            return validateNext(object, validatorInfos);
        }
        validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.PASSED));
        return validateNext(object, validatorInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_EMAIL_IS_NULL_VALIDATOR;
    }

}

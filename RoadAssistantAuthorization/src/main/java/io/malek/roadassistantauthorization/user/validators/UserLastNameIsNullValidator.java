package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.dtos.LastName;
import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.ValidatorInfo;
import io.malek.roadassistantauthorization.user.enums.ValidatorName;
import io.malek.roadassistantauthorization.user.enums.ValidatorStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static java.util.Objects.isNull;

@Slf4j
class UserLastNameIsNullValidator extends Validator<UserCreationRequest, ValidatorInfo, ValidatorName> {

    @Override
    protected Set<ValidatorInfo> validate(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        log.info("Starting validation lastName of user with process id: [{}]", object.getProcessId().value());
        final LastName lastName = object.getLastName();
        ValidatorName validatorName = getValidatorName();
        if (isNull(lastName)) {
            log.error("User creation process with id: [{}] failed because lastName is null", object.getProcessId().value());
            validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.FAILED));
            return validateNext(object, validatorInfos);
        }
        validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.PASSED));
        return validateNext(object, validatorInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return ValidatorName.USER_LAST_NAME_VALIDATOR;
    }
}

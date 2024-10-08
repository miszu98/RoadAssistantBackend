package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.dtos.Password;
import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.ValidatorInfo;
import io.malek.roadassistantauthorization.user.enums.ValidatorName;
import io.malek.roadassistantauthorization.user.enums.ValidatorStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.enums.ValidatorName.USER_PASSWORD_IS_NULL_VALIDATOR;
import static java.util.Objects.isNull;

@Slf4j
class UserPasswordIsNullValidator extends Validator<UserCreationRequest, ValidatorInfo, ValidatorName> {

    @Override
    protected Set<ValidatorInfo> validate(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        log.info("Starting validation password of user with process id: [{}]", object.getProcessId().value());
        final Password password = object.getPassword();
        ValidatorName validatorName = getValidatorName();
        if (isNull(password)) {
            log.error("User creation process with id: [{}] failed because password is null", object.getProcessId().value());
            validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.FAILED));
            return validateNext(object, validatorInfos);
        }
        validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.PASSED));
        return validateNext(object, validatorInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_PASSWORD_IS_NULL_VALIDATOR;
    }

}

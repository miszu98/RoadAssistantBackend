package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.ProcessId;
import io.malek.roadassistantauthorization.user.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.ValidatorName.USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR;
import static java.util.Objects.isNull;

@Slf4j
class UserCreationProcessIdIsNullValidator extends Validator<UserCreationRequest, ValidatorInfo, ValidatorName> {

    @Override
    protected Set<ValidatorInfo> validate(UserCreationRequest object, Set<ValidatorInfo> validatorInfos) {
        log.info("Starting validation processId of user creation request");
        final ProcessId processId = object.getProcessId();
        ValidatorName validatorName = getValidatorName();
        if (isNull(processId)) {
            log.error("User creation request failed because processId is null");
            throw new ProcessCannotBeExecuteException(validatorName.getMessage());
        }
        validatorInfos.add(ValidatorInfo.of(validatorName, ValidatorStatus.PASSED));
        return validateNext(object, validatorInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_CREATION_REQUEST_PROCESS_ID_IS_NULL_VALIDATOR;
    }

}

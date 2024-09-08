package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.UserCreationRequest;
import io.malek.roadassistantauthorization.user.ValidatorInfo;
import io.malek.roadassistantauthorization.user.ValidatorName;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;


@Slf4j
class UserCreationValidatorService implements UserValidatorService {
    private Validator<UserCreationRequest, ValidatorInfo, ValidatorName> validator;

    UserCreationValidatorService() {
        validator = Validator.link(
            new UserCreationProcessIdIsNullValidator(),
            new UserEmailIsNullValidator(),
            new UserPasswordIsNullValidator(),
            new UserRepeatPasswordIsNullValidator(),
            new UserPasswordsEqualityValidator(),
            new UserFirstNameIsNullValidator(),
            new UserLastNameIsNullValidator()
        );
    }

    @Override
    public Set<ValidatorInfo> validateRequiredFields(UserCreationRequest userCreationRequest) {
        log.info("Starting validation process for UserCreationRequest");
        return validator.validate(userCreationRequest, new HashSet<>());
    }

}

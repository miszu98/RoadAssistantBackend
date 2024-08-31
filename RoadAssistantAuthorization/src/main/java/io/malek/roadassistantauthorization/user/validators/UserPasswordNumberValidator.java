package io.malek.roadassistantauthorization.user.validators;

import io.malek.Password;
import io.malek.roadassistantauthorization.user.ValidatorInfo;
import io.malek.roadassistantauthorization.user.ValidatorName;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.ValidatorName.USER_PASSWORD_NUMBER_VALIDATOR;

@Slf4j
class UserPasswordNumberValidator extends Validator<Password, ValidatorInfo, ValidatorName> {

    @Override
    protected Set<ValidatorInfo> validate(Password object, Set<ValidatorInfo> validatorsInfos) {
        return null;
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_PASSWORD_NUMBER_VALIDATOR;
    }
}

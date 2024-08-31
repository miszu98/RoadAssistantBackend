package io.malek.roadassistantauthorization.user.validators;

import io.malek.Password;
import io.malek.roadassistantauthorization.user.ValidatorInfo;
import io.malek.roadassistantauthorization.user.ValidatorName;
import io.malek.roadassistantauthorization.user.ValidatorStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
class UserPasswordLengthValidator extends Validator<Password, ValidatorInfo, ValidatorName> {
    private static final int MIN_RANGE = 8;
    private static final int MAX_RANGE = 16;

    @Override
    protected Set<ValidatorInfo> validate(Password object, Set<ValidatorInfo> validatorsInfos) {
        boolean passwordHasCorrectLength = object.length() >= MIN_RANGE && object.length() <= MAX_RANGE;
        ValidatorName validatorName = getValidatorName();
        ValidatorInfo validatorInfo = ValidatorInfo.of(validatorName, ValidatorStatus.FAILED);
        if (passwordHasCorrectLength) {
            validatorInfo = ValidatorInfo.of(validatorName, ValidatorStatus.PASSED);
        }
        validatorsInfos.add(validatorInfo);
        return validateNext(object, validatorsInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return ValidatorName.USER_PASSWORD_LENGTH_VALIDATOR;
    }
}

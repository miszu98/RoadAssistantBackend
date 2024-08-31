package io.malek.roadassistantauthorization.user.validators;

import io.malek.Password;
import io.malek.roadassistantauthorization.user.ValidatorInfo;
import io.malek.roadassistantauthorization.user.ValidatorName;
import io.malek.roadassistantauthorization.user.ValidatorStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;

import static io.malek.roadassistantauthorization.user.ValidatorName.USER_PASSWORD_SPECIAL_CHARS_VALIDATOR;

@Slf4j
class UserPasswordSpecialCharValidator extends Validator<Password, ValidatorInfo, ValidatorName> {
    private static final String[] SPECIAL_CHARS_ALLOWED = {".","!","#","@","$","%","^","&","*","()","/"};

    @Override
    protected Set<ValidatorInfo> validate(Password object, Set<ValidatorInfo> validatorsInfos) {
        boolean passwordNotContainsSpecialChars = Arrays.stream(SPECIAL_CHARS_ALLOWED).noneMatch(object::contains);
        ValidatorName validatorName = getValidatorName();
        ValidatorInfo validatorInfo = ValidatorInfo.of(validatorName, ValidatorStatus.PASSED);
        if (passwordNotContainsSpecialChars) {
            validatorInfo = ValidatorInfo.of(validatorName, ValidatorStatus.FAILED);
        }
        validatorsInfos.add(validatorInfo);
        return validateNext(object, validatorsInfos);
    }

    @Override
    protected ValidatorName getValidatorName() {
        return USER_PASSWORD_SPECIAL_CHARS_VALIDATOR;
    }
}

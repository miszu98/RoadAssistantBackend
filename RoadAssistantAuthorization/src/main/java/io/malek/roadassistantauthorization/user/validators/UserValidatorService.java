package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.UserCreationRequest;
import io.malek.roadassistantauthorization.user.ValidatorInfo;

import java.util.Set;

public interface UserValidatorService {

    Set<ValidatorInfo> validate(UserCreationRequest userCreationRequest);

}

package io.malek.roadassistantauthorization.user.validators;

import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.ValidatorInfo;

import java.util.Set;

public interface UserValidatorService {

    Set<ValidatorInfo> validateRequiredFields(UserCreationRequest userCreationRequest);

}

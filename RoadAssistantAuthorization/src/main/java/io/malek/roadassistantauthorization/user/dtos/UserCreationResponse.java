package io.malek.roadassistantauthorization.user.dtos;


import io.malek.roadassistantauthorization.user.enums.ValidatorStatus;
import io.malek.roadassistantauthorization.user.enums.ProcessStatus;

import java.util.HashSet;
import java.util.Set;

public record UserCreationResponse(ProcessStatus processStatus, Set<ValidatorInfo> validatorInfos) {

    public static UserCreationResponse of(Set<ValidatorInfo> validatorInfos) {
        return new UserCreationResponse(loadProcessStatus(validatorInfos), validatorInfos);
    }

    private static ProcessStatus loadProcessStatus(Set<ValidatorInfo> validatorInfos) {
        boolean allValidatorsPassed = validatorInfos.stream()
                .map(ValidatorInfo::status)
                .allMatch(validatorStatus -> validatorStatus == ValidatorStatus.PASSED);
        return allValidatorsPassed ? ProcessStatus.COMPLETED : ProcessStatus.FAILURE;
    }

    public static UserCreationResponse empty() {
        return new UserCreationResponse(ProcessStatus.FAILURE, new HashSet<>());
    }

}

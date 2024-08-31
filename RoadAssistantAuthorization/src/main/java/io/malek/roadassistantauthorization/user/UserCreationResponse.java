package io.malek.roadassistantauthorization.user;


import java.util.Set;

record UserCreationResponse(ProcessStatus processStatus, Set<ValidatorInfo> validatorInfos) {

    static UserCreationResponse of(Set<ValidatorInfo> validatorInfos) {
        return new UserCreationResponse(loadProcessStatus(validatorInfos), validatorInfos);
    }

    private static ProcessStatus loadProcessStatus(Set<ValidatorInfo> validatorInfos) {
        boolean allValidatorsPassed = validatorInfos.stream()
                .map(ValidatorInfo::status)
                .allMatch(validatorStatus -> validatorStatus == ValidatorStatus.PASSED);
        return allValidatorsPassed ? ProcessStatus.COMPLETED : ProcessStatus.FAILURE;
    }

}

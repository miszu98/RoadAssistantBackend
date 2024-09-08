package io.malek.roadassistantauthorization.user;

import lombok.RequiredArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RequiredArgsConstructor(staticName = "assertThatUserCreationResponse")
class UserCreationResponseAssert {
    private final UserCreationResponse userCreationResponse;

    UserCreationResponseAssert isUserCreationProcessCompleted() {
        assertEquals(ProcessStatus.COMPLETED, userCreationResponse.processStatus());
        assertFalse(userCreationResponse.validatorInfos().isEmpty());
        return this;
    }

    UserCreationResponseAssert isUserCreationProcessFailure() {
        assertEquals(ProcessStatus.FAILURE, userCreationResponse.processStatus());
        assertFalse(userCreationResponse.validatorInfos().isEmpty());
        return this;
    }

}

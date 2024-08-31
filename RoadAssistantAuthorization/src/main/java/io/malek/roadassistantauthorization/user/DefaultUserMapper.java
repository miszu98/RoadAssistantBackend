package io.malek.roadassistantauthorization.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class DefaultUserMapper implements UserMapper {
    private final ObjectMapper objectMapper;

    @Override
    public User mapToUser(UserCreationRequest userCreationRequest) {
        log.info("Mapping UserCreationRequest to User with processId: [{}]", userCreationRequest.getProcessId());
        return objectMapper.convertValue(userCreationRequest, User.class);
    }

}

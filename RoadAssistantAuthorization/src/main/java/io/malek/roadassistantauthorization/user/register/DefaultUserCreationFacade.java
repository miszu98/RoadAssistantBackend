package io.malek.roadassistantauthorization.user.register;

import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.UserCreationResponse;
import io.malek.roadassistantauthorization.user.dtos.ValidatorInfo;
import io.malek.roadassistantauthorization.user.enums.ProcessStatus;
import io.malek.roadassistantauthorization.user.validators.UserValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
class DefaultUserCreationFacade implements UserCreationFacade {
    private final UserRepository userRepository;
    private final UserValidatorService userValidatorService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserCreationResponse create(UserCreationRequest userCreationRequest) {
        log.info("Starting user creation process");
        Set<ValidatorInfo> validatorInfos = userValidatorService.validateRequiredFields(userCreationRequest);
        UserCreationResponse userCreationResponse = UserCreationResponse.of(validatorInfos);
        return registerUser(userCreationRequest, userCreationResponse);
    }

    private UserCreationResponse registerUser(UserCreationRequest userCreationRequest, UserCreationResponse userCreationResponse) {
        if (userCreationResponse.processStatus() == ProcessStatus.COMPLETED) {
            log.info("User creation request passed all validation and save to database, process with id: [{}] finished", userCreationRequest.getProcessId());
            User user = userMapper.mapToUser(userCreationRequest);
            // todo set roles
            userRepository.save(user);
            return userCreationResponse;
        }
        log.warn("User creation request failed validations");
        return userCreationResponse;
    }

}

package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.validators.UserValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static io.malek.roadassistantauthorization.user.data_generators.UserCreationRequestDataGeneratorUtil.*;
import static io.malek.roadassistantauthorization.user.data_generators.UserDataGenerator.getUser;
import static org.mockito.Mockito.*;
import static io.malek.roadassistantauthorization.user.UserCreationResponseAssert.*;

@ExtendWith(MockitoExtension.class)
class DefaultUserCreationFacadeTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserValidatorService userValidatorService;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    DefaultUserCreationFacade defaultUserCreationFacade;

    @Test
    void shouldCompleteUserCreationProcessWhenAllValidatorsPassed() {
        final UserCreationRequest userCreationRequest = getCorrectUser();
        final User user = getUser();
        final Set<ValidatorInfo> validatorInfos = getValidatorsInfoAllPassed();

        when(userValidatorService.validateRequiredFields(userCreationRequest)).thenReturn(validatorInfos);
        when(userMapper.mapToUser(userCreationRequest)).thenReturn(user);

        UserCreationResponse userCreationResponse = defaultUserCreationFacade.create(userCreationRequest);

        verify(userMapper, times(1)).mapToUser(userCreationRequest);
        verify(userRepository, times(1)).save(any(User.class));

        assertThatUserCreationResponse(userCreationResponse).isUserCreationProcessCompleted();
    }

    @Test
    void shouldFailureUserCreationProcessWhenAnyValidatorFailed() {
        final UserCreationRequest userCreationRequest = getUserWithNullEmail();
        final Set<ValidatorInfo> validatorInfos = getValidatorsInfoEmailNullValidatorFailed();

        when(userValidatorService.validateRequiredFields(userCreationRequest)).thenReturn(validatorInfos);

        UserCreationResponse userCreationResponse = defaultUserCreationFacade.create(userCreationRequest);

        verifyNoInteractions(userMapper);
        verifyNoInteractions(userRepository);

        assertThatUserCreationResponse(userCreationResponse).isUserCreationProcessFailure();
    }

}
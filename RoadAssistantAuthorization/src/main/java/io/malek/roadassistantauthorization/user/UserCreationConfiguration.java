package io.malek.roadassistantauthorization.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.roadassistantauthorization.user.validators.UserValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class UserCreationConfiguration {
    private final UserValidatorService userValidatorService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Bean
    UserMapper userMapper() {
        return new DefaultUserMapper(objectMapper);
    }

    @Bean
    UserCreationFacade userCreationFacade() {
        return new DefaultUserCreationFacade(userRepository, userValidatorService, userMapper());
    }

}

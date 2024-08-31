package io.malek.roadassistantauthorization.user.validators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserValidatorsConfiguration {

    @Bean
    UserValidatorService userValidatorService() {
        return new UserCreationValidatorService();
    }

}

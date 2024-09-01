package io.malek.roadassistantauthorization.user;

import io.malek.HashPassword;
import io.malek.Password;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
interface  UserMapper {

    @Mapping(target = "password", expression = "java(hashPassword(userCreationRequest.getPassword()))")
    User mapToUser(UserCreationRequest userCreationRequest);

    default HashPassword hashPassword(Password a) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return HashPassword.of(passwordEncoder.encode(a.value()));
    }
}

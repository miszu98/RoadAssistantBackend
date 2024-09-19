package io.malek.roadassistantauthorization.user.data_generators;

import io.malek.roadassistantauthorization.user.*;
import io.malek.roadassistantauthorization.user.dtos.Email;
import io.malek.roadassistantauthorization.user.dtos.FirstName;
import io.malek.roadassistantauthorization.user.dtos.HashPassword;
import io.malek.roadassistantauthorization.user.dtos.LastName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDataGenerator {

    public static User getUser() {
        return User.builder()
                .firstName(FirstName.of("Roman"))
                .lastName(LastName.of("Nowak"))
                .email(Email.of("roman.nowak@gmail.com"))
                .password(HashPassword.of("$2a$12$/HvJ1aZU0b.CwLVl1f4/V.g0v6AbybjI17JPsy8Fp9nnHcr4M8Y4."))
                .build();
    }

}

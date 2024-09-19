package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.UserCreationResponse;

interface UserCreationFacade {

    UserCreationResponse create(UserCreationRequest userCreationRequest);

}

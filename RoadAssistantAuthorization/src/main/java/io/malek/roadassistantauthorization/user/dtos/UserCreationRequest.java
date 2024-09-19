package io.malek.roadassistantauthorization.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@AllArgsConstructor
@Tag(name = "UserCreationRequest", description = "User creation request for register account in RoadAssistant app")
public class UserCreationRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, implementation = Email.class, description = "User email", example = "john.smith@gmail.com")
    Email email;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, implementation = Password.class, description = "User password", example = "dasgwefra123/A.")
    Password password;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, implementation = Password.class, description = "User repeat password should be the same with first password", example = "dasgwefra123/A.")
    Password repeatPassword;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, implementation = FirstName.class, description = "User first name", example = "John")
    FirstName firstName;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, implementation = LastName.class, description = "User last name", example = "Smith")
    LastName lastName;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, implementation = ProcessId.class, description = "User creation unique process uuid per request", example = "3da09869-cbce-418f-af64-a56830023d3d")
    ProcessId processId;

}

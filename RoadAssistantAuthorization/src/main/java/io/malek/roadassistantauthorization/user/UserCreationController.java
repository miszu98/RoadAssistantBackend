package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.UserCreationRequest;
import io.malek.roadassistantauthorization.user.dtos.UserCreationResponse;
import io.malek.roadassistantauthorization.utils.HttpStatusCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "UserCreationController", description = "Rest controller for user creation API management")
class UserCreationController {
    private final UserCreationFacade userCreationFacade;

    @PostMapping
    @Operation(description = "User create account operation", responses = {
            @ApiResponse(responseCode = HttpStatusCodes.CREATED, description = "User account successfully created, passed all validation steps",
                            content = @Content(schema = @Schema(implementation = UserCreationResponse.class))),
            @ApiResponse(responseCode = HttpStatusCodes.CONFLICT, description = "User account unsuccessfully created, failed all validation steps",
                            content = @Content(schema = @Schema(implementation = UserCreationResponse.class)))
    })
    public ResponseEntity<UserCreationResponse> create(@RequestBody UserCreationRequest userCreationRequest) {
        return ResponseEntity.ok().body(userCreationFacade.create(userCreationRequest));
    }

}

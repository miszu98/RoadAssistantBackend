package io.malek.roadassistantauthorization.requests;

import io.malek.roadassistantauthorization.requests.dtos.*;
import io.malek.roadassistantauthorization.requests.enums.RequestType;
import io.malek.roadassistantauthorization.user.dtos.UserCreationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestGenerator {

    public static RequestDTO createUserRegisterRequestDto() throws IOException {
        return new RequestDTO(
                RequestData.of("UserCreationRequest"),
                ResponseData.of(ResponseEntity.status(HttpStatus.OK).body(UserCreationResponse.empty()).toString()),
                RequestType.USER_REGISTER,
                RequestUid.newOne(),
                RequestStatusCode.of(200)
        );
    }

    public static Request createUserRegisterRequest(RequestDTO requestDTO) throws IOException {
        return Request.builder()
                .requestUid(requestDTO.requestUid())
                .requestData(RequestData.of(new String(Files.readAllBytes(Paths.get("src/test/resources/data/requests/user_register_200_request.json")))))
                .responseData(ResponseData.of(new String(Files.readAllBytes(Paths.get("src/test/resources/data/requests/user_register_200_response.json")))))
                .type(requestDTO.type())
                .code(requestDTO.code())
                .build();
    }

}

package io.malek.roadassistantauthorization.requests;

import io.malek.roadassistantauthorization.IntegrationTestConfig;
import io.malek.roadassistantauthorization.requests.dtos.RequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultRequestRegisterFacadeTestIT extends IntegrationTestConfig {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestRegisterFacade requestRegisterFacade;

    @Test
    void shouldCorrectlySavedRequest() throws IOException {
        final RequestDTO requestDTO = RequestGenerator.createUserRegisterRequestDto();

        requestRegisterFacade.registerRequest(requestDTO);

        assertEquals(1, requestRepository.findAll().size());
    }

}

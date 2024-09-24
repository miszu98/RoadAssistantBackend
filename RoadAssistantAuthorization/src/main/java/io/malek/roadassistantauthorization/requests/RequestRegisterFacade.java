package io.malek.roadassistantauthorization.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.malek.roadassistantauthorization.requests.dtos.RequestDTO;

public interface RequestRegisterFacade {

    void registerRequest(RequestDTO requestDTO) throws JsonProcessingException;

}

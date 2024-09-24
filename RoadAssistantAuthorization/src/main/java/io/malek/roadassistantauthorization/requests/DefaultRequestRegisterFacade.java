package io.malek.roadassistantauthorization.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.malek.Timestamp;
import io.malek.roadassistantauthorization.requests.dtos.RequestDTO;
import io.malek.roadassistantauthorization.requests.dtos.RequestData;
import io.malek.roadassistantauthorization.requests.dtos.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Slf4j
@RequiredArgsConstructor
class DefaultRequestRegisterFacade implements RequestRegisterFacade {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final ObjectMapper objectMapper;
    private final Clock clock;

    @Override
    @Transactional
    public void registerRequest(RequestDTO requestDTO) throws JsonProcessingException {
        Request request = requestMapper.mapToRequest(requestDTO);
        request.setCreatedAt(Timestamp.now(clock));
        request.setRequestData(RequestData.of(objectMapper.writeValueAsString(requestDTO.requestData())));
        request.setResponseData(ResponseData.of(objectMapper.writeValueAsString(requestDTO.responseData())));
        requestRepository.save(request);
    }

}

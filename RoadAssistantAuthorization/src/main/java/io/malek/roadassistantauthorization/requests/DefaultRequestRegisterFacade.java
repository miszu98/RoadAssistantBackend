package io.malek.roadassistantauthorization.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
class DefaultRequestRegisterFacade implements RequestRegisterFacade {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    @Transactional
    public void registerRequest(RequestDTO requestDTO) {
        Request request = requestMapper.mapToRequest(requestDTO);
        requestRepository.save(request);
    }

}

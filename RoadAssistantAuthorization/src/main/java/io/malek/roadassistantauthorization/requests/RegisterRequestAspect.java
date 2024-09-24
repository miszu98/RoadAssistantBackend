package io.malek.roadassistantauthorization.requests;

import io.malek.Process;
import io.malek.roadassistantauthorization.requests.annotations.RegisterRequest;
import io.malek.roadassistantauthorization.requests.dtos.*;
import io.malek.roadassistantauthorization.requests.enums.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import static java.util.Objects.nonNull;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
class RegisterRequestAspect {
    private final RequestRegisterFacade requestRegisterFacade;

    @Around("@annotation(registerRequest)")
    protected Object handleRegisterRequest(ProceedingJoinPoint joinPoint, RegisterRequest registerRequest) throws Throwable {
        log.info("Handle register request: [{}] for type: [{}]", Arrays.toString(joinPoint.getArgs()), registerRequest.requestType());
        final Process process = (Process) joinPoint.getArgs()[0];
        final ResponseEntity responseEntity = (ResponseEntity) joinPoint.proceed();
        final RequestDTO requestDto = createRequestDto(process, responseEntity, registerRequest.requestType());
        requestRegisterFacade.registerRequest(requestDto);
        return responseEntity;
    }

    private RequestDTO createRequestDto(Process process, ResponseEntity responseEntity, RequestType requestType) {
        return new RequestDTO(
                RequestData.of(process.toString()),
                ResponseData.of(nonNull(responseEntity.getBody()) ? responseEntity.getBody().toString() : null),
                requestType,
                RequestUid.of(process.getProcessId().value()),
                RequestStatusCode.of(responseEntity.getStatusCode().value())
        );
    }

}

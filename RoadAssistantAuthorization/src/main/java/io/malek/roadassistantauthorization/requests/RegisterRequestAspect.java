package io.malek.roadassistantauthorization.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
class RegisterRequestAspect {
//    private final RequestRegisterFacade requestRegisterFacade;

    @After("@annotation(registerRequest)")
    void handleRegisterRequest(JoinPoint joinPoint, RegisterRequest registerRequest) {
        log.info("Handle register request: [{}] for type: [{}]", Arrays.toString(joinPoint.getArgs()), registerRequest.requestType());
        System.out.println(joinPoint.getArgs()[0]);
//        requestRegisterFacade.registerRequest();

    }

}

package io.malek.roadassistantauthorization.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(ProcessCannotBeExecuteException.class)
    ResponseEntity<ExceptionResponse> handleProcessCannotBeExecuteException(ProcessCannotBeExecuteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionResponse.of(LocalDateTime.now(), List.of(ex.getMessage())));
    }

}

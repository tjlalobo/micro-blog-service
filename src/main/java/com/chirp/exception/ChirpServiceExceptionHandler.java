package com.chirp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ChirpServiceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ChirpNotFoundException.class)
    public ErrorMessage handleCustomerNotFoundException(ChirpNotFoundException e) {
        log.error(e.getMessage(), e);

        return ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
       return e.getBindingResult().getFieldErrors().stream()
                .map(error -> FieldErrorMessage.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }

}

package com.chirp.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldErrorMessage {

    private int status;

    private String field;

    private String message;

}

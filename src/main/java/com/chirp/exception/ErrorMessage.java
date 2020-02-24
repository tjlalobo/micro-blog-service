package com.chirp.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorMessage {

    private int status;

    private String message;

}

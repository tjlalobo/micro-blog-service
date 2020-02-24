package com.chirp.exception;

import lombok.Getter;

@Getter
public class ChirpNotFoundException extends Exception {

    private final String message = "Chirp not found";

}

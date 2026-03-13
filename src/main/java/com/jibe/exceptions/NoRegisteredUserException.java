package com.jibe.exceptions;

public class NoRegisteredUserException extends RuntimeException {
    public NoRegisteredUserException(String message) {
        super(message);
    }
}

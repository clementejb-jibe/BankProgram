package com.jibe.exceptions;

public class EmptyTransactionException extends RuntimeException {
    public EmptyTransactionException(String message) {
        super(message);
    }
}

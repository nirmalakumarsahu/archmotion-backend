package com.archmotion.exception.custom;

public class ResourceOperationNotAllowException extends RuntimeException {
    public ResourceOperationNotAllowException(String message) {
        super(message);
    }
}

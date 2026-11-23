package com.archmotion.exception.custom;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(message);
    }
}

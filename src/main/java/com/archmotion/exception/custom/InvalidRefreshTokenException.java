package com.archmotion.exception.custom;

public class InvalidRefreshTokenException extends ApiException {
    public InvalidRefreshTokenException() {
        super("Invalid refresh token");
    }
}

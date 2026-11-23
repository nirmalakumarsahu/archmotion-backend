package com.archmotion.exception.custom;


import com.archmotion.exception.dto.ApiError;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(ApiError apiError) {
        super(apiError);
    }
}

package com.archmotion.exception.custom;

import com.archmotion.exception.dto.ApiError;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ApiError apiError;

    public ApiException(ApiError apiError) {
        super(apiError.detail());
        this.apiError = apiError;
    }

    public ApiException(String message) {
        super(message);
        apiError = ApiError.build(message, message, null);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        apiError = ApiError.build(message, message, null);
    }

}

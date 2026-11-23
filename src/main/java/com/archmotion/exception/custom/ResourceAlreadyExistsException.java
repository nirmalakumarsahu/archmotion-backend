package com.archmotion.exception.custom;

import com.archmotion.exception.dto.ApiError;

public class ResourceAlreadyExistsException extends ApiException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(ApiError apiError) {
        super(apiError);
    }
}

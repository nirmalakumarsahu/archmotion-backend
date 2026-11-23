package com.archmotion.exception.custom;

import com.archmotion.exception.dto.ApiError;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(ApiError apiError) {
        super(apiError);
    }
}

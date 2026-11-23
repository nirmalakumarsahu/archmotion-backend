package com.archmotion.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiResponse<T>(
        Instant timestamp,
        boolean success,
        String message,
        T data
) {

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .timestamp(Instant.now())
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse<Void> success(String message) {
        return ApiResponse.<Void>builder()
                .timestamp(Instant.now())
                .success(true)
                .message(message)
                .build();
    }

}

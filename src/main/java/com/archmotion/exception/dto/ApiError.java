package com.archmotion.exception.dto;

import lombok.Builder;

@Builder
public record ApiError(
        String title,
        String detail,
        String code
) {

    public static ApiError build(String title, String detail, String code) {
        return ApiError.builder()
                .title(title)
                .detail(detail)
                .code(code)
                .build();
    }

    public static ApiError build() {
        return ApiError.builder().build();
    }

}

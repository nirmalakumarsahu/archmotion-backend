package com.archmotion.dto.request;

import lombok.Builder;

@Builder
public record UserRequest(
        String username,
        String fullName,
        String email,
        String password
) {
}

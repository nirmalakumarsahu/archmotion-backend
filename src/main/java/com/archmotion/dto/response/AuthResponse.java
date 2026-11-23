package com.archmotion.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Instant expiresAt
) {
}

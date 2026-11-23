package com.archmotion.dto.response;

import com.archmotion.security.constant.Roles;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID uuid,
        String username,
        String fullName,
        String email,
        Roles role
) {
}

package com.archmotion.security.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Roles {
    SUPER_ADMIN("Super Admin"),
    USER("User");

    private final String value;
}

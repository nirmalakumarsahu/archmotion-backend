package com.archmotion.security.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Claims {
    USERNAME("username"),
    ROLES("roles"),
    EMAIL("email");

    private final String value;
}

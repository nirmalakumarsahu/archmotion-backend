package com.archmotion.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
        String issuer,
        Duration accessTokenExpiration,
        Duration refreshTokenExpiration,
        String privateKeyPath,
        String publicKeyPath
) {
}

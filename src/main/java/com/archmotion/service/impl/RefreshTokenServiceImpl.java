package com.archmotion.service.impl;

import com.archmotion.entity.RefreshToken;
import com.archmotion.entity.User;
import com.archmotion.exception.custom.InvalidRefreshTokenException;
import com.archmotion.repository.RefreshTokenRepository;
import com.archmotion.repository.UserRepository;
import com.archmotion.security.dto.CustomUserPrincipal;
import com.archmotion.security.properties.JwtProperties;
import com.archmotion.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    @Transactional
    public String create(CustomUserPrincipal user) {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);

        String rawToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setTokenHash(hashToken(rawToken));
        refreshToken.setExpiryAt(Instant.now().plus(jwtProperties.refreshTokenExpiration()));
        refreshToken.setUserId(user.getId());
        refreshToken.setCreatedAt(Instant.now());
        refreshTokenRepository.save(refreshToken);

        return rawToken;
    }

    @Override
    public User validate(String rawToken) {
        String tokenHash = hashToken(rawToken);
        RefreshToken token = refreshTokenRepository.findByTokenHash(tokenHash)
                .orElseThrow(InvalidRefreshTokenException::new);

        if (token.getRevoked() || token.getExpiryAt().isBefore(Instant.now())) {
            throw new InvalidRefreshTokenException();
        }

        return userRepository.findById(token.getUserId())
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    @Override
    public void revoke(String rawToken) {
        String tokenHash = hashToken(rawToken);
        refreshTokenRepository.findByTokenHash(tokenHash)
                .ifPresent(refreshToken -> {
                    refreshToken.setRevoked(true);
                    refreshToken.setRevokedAt(Instant.now());
                    refreshTokenRepository.save(refreshToken);
                });
    }

    private String hashToken(String rawToken) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(rawToken.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while hashing the token", e);
        }
    }

}

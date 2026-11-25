package com.archmotion.security.service;

import com.archmotion.security.constant.Claims;
import com.archmotion.security.dto.CustomUserPrincipal;
import com.archmotion.security.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;
    private final JwtEncoder jwtEncoder;

    public String generateToken(CustomUserPrincipal user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtProperties.issuer())
                .subject(user.userUuid().toString())
                .issuedAt(now)
                .expiresAt(now.plus(jwtProperties.accessTokenExpiration()))
                .claim(Claims.USERNAME.getValue(), user.username())
                .claim(Claims.EMAIL.getValue(), user.email())
                .claim(Claims.ROLES.getValue(), user.role())
                .build();

        JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS256).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

}

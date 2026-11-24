package com.archmotion.service.impl;

import com.archmotion.constant.AppConstants;
import com.archmotion.dto.request.GoogleAuthRequest;
import com.archmotion.dto.request.LoginRequest;
import com.archmotion.dto.request.UserRequest;
import com.archmotion.dto.response.AuthResponse;
import com.archmotion.entity.User;
import com.archmotion.exception.custom.ResourceAlreadyExistsException;
import com.archmotion.exception.custom.UnauthorizedException;
import com.archmotion.exception.dto.ApiError;
import com.archmotion.repository.UserRepository;
import com.archmotion.security.constant.Roles;
import com.archmotion.security.dto.CustomUserPrincipal;
import com.archmotion.security.properties.JwtProperties;
import com.archmotion.security.service.JwtService;
import com.archmotion.service.AuthService;
import com.archmotion.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        if (!authentication.isAuthenticated()) {
            throw new UnauthorizedException(
                    ApiError.builder()
                            .title("User not Authorized")
                            .detail("Invalid username or password")
                            .code("INVALID_USERNAME_OR_PASSWORD")
                            .build()
            );
        }

        CustomUserPrincipal user = (CustomUserPrincipal) authentication.getPrincipal();
        return generateAuthResponse(user);
    }

    @Override
    @Transactional
    public AuthResponse register(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.username())) {
           throw new ResourceAlreadyExistsException("Username already exists");
        }

        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .username(userRequest.username())
                .email(userRequest.email())
                .fullName(userRequest.fullName())
                .password(passwordEncoder.encode(userRequest.password()))
                .role(Roles.USER)
                .build();
        user = userRepository.save(user);

        CustomUserPrincipal userPrincipal = CustomUserPrincipal.build(user);
        return generateAuthResponse(userPrincipal);
    }

    @Override
    public AuthResponse googleAuth(GoogleAuthRequest googleAuthRequest) {
        return null;
    }

    private AuthResponse generateAuthResponse(CustomUserPrincipal user) {
        String accessToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.create(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(AppConstants.TOKEN_TYPE_BEARER)
                .expiresAt(Instant.now().plus(jwtProperties.accessTokenExpiration()))
                .build();
    }

}

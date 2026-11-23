package com.archmotion.controller;

import com.archmotion.dto.request.GoogleAuthRequest;
import com.archmotion.dto.request.LoginRequest;
import com.archmotion.dto.request.UserRequest;
import com.archmotion.dto.response.ApiResponse;
import com.archmotion.dto.response.AuthResponse;
import com.archmotion.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.success("Login successful", authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@RequestBody UserRequest userRequest) {
        return ApiResponse.success("User registered successfully", authService.register(userRequest));
    }

    @PostMapping("/google")
    public ApiResponse<AuthResponse> googleAuth(@RequestBody GoogleAuthRequest googleAuthRequest) {
        return null;
    }


}
package com.archmotion.service;

import com.archmotion.dto.request.GoogleAuthRequest;
import com.archmotion.dto.request.LoginRequest;
import com.archmotion.dto.request.UserRequest;
import com.archmotion.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(UserRequest userRequest);

    AuthResponse googleAuth(GoogleAuthRequest googleAuthRequest);

}

package com.archmotion.service;

import com.archmotion.entity.User;
import com.archmotion.security.dto.CustomUserPrincipal;

public interface RefreshTokenService {

    String create(CustomUserPrincipal user);

    User validate(String rawToken);

    void revoke(String rawToken);

}

package com.archmotion.security.util;

import com.archmotion.security.dto.CustomUserPrincipal;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@UtilityClass
public class SecurityUtil {

    public CustomUserPrincipal getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null &&
                authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            return customUserPrincipal;
        }

        throw new AuthenticationCredentialsNotFoundException(
                "User is not Authenticated"
        );
    }

    public UUID getCurrentUserUuid() {
        return getCurrentUser().userUuid();
    }

}

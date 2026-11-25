package com.archmotion.security.converter;

import com.archmotion.constant.AppConstants;
import com.archmotion.security.constant.Claims;
import com.archmotion.security.dto.CustomUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        String roles = jwt.getClaimAsString(Claims.ROLES.getValue());
        log.info("roles : {}", roles);
        grantedAuthorities.add(new SimpleGrantedAuthority(AppConstants.ROLE_PREFIX + roles));

        String username = jwt.getClaimAsString(Claims.USERNAME.getValue());
        String email = jwt.getClaimAsString(Claims.EMAIL.getValue());
        UUID userUuid = UUID.fromString(Objects.requireNonNull(jwt.getSubject()));

        log.info("Username: {}, email: {}, user-uuid: {}", username, email, userUuid);

        CustomUserPrincipal principal = CustomUserPrincipal.builder()
                .username(username)
                .email(email)
                .userUuid(userUuid)
                .role(roles)
                .authorities(grantedAuthorities)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, jwt, grantedAuthorities);
    }

}

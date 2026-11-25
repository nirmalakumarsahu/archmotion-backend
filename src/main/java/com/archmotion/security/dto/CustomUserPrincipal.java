package com.archmotion.security.dto;

import com.archmotion.constant.AppConstants;
import com.archmotion.entity.User;
import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Builder
public record CustomUserPrincipal(
        Long id,
        UUID userUuid,
        String username,
        String password,
        String email,
        String role,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public static CustomUserPrincipal build(User user) {
        return CustomUserPrincipal.builder()
                .id(user.getId())
                .userUuid(user.getUuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(user.getRole().name())
                .authorities(
                        Collections.singleton(new SimpleGrantedAuthority(
                                AppConstants.ROLE_PREFIX + user.getRole()))
                )
                .build();
    }

    @NullMarked
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @NullMarked
    public String getUsername() {
        return username;
    }

}

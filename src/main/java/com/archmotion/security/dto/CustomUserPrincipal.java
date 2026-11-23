package com.archmotion.security.dto;

import com.archmotion.entity.User;
import lombok.Builder;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Builder
public record CustomUserPrincipal(
        Long id,
        UUID userUuid,
        String username,
        String password,
        String email,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public static CustomUserPrincipal build(User user) {
        return CustomUserPrincipal.builder()
                .id(user.getId())
                .userUuid(user.getUuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .authorities(null)
                .build();
    }

    public Long getId() {
        return id;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public String getEmail() {
        return email;
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

package com.archmotion.security.service;

import com.archmotion.repository.UserRepository;
import com.archmotion.security.dto.CustomUserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    @NullMarked
    public CustomUserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", username);
        return userRepository.findByUsernameOrEmail(username, username)
                .map(CustomUserPrincipal::build)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username: " + username));
    }

}

package com.archmotion.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Table(
        name = "refresh_tokens",
        indexes = {
                @Index(name = "idx_refresh_token_user_id", columnList = "user_id"),
                @Index(name = "idx_refresh_token_token_hash", columnList = "token_hash")
        }
)

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String tokenHash;

    private Instant expiryAt;

    @Builder.Default
    private Boolean revoked = false;

    private Instant createdAt;

    private Instant revokedAt;
}

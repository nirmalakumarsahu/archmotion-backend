package com.archmotion.entity;

import com.archmotion.auditing.Auditable;
import com.archmotion.constant.AuthProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(
        name = "user_auth_providers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_provider_provider_subject", columnNames = {"user_id"})
        }
)

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthProvider extends Auditable<UUID> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private AuthProvider authProvider;

    @Column(name = "provider_subject", nullable = false)
    private String providerSubject;

    private String providerEmail;

    private boolean emailVerified;
}

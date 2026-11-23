package com.archmotion.entity;

import com.archmotion.auditing.Auditable;
import com.archmotion.security.constant.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_user_username", columnList = "username"),
                @Index(name = "idx_user_email", columnList = "email")
        }
)

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable<UUID> {
    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Roles role;
}

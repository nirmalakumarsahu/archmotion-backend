package com.archmotion.auditing;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction("deleted = false")

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class Auditable<U> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    @Column(nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @CreatedBy
    private U createdBy;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedBy
    private U lastModifiedBy;

    @LastModifiedDate
    private Instant lastModifiedAt;

    @Builder.Default
    private Boolean deleted = false;

    private U deletedBy;

    private Instant deletedAt;

    public void markDeleted(U deletedBy) {
        this.deleted = true;
        this.deletedBy = deletedBy;
        this.deletedAt = Instant.now();
    }

    public void restore() {
        this.deleted = false;
        this.deletedBy = null;
        this.deletedAt = null;
    }

}

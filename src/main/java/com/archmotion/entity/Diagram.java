package com.archmotion.entity;

import com.archmotion.auditing.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(
        name = "diagrams",
        indexes = {
            @Index(name = "idx_diagram_name", columnList = "name")
        }
)

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Diagram extends Auditable<UUID> {
    @Column(nullable = false)
    private String name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false, columnDefinition = "jsonb")
    private String diagramData;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

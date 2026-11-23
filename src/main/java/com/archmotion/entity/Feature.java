package com.archmotion.entity;

import com.archmotion.auditing.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "features")

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Feature extends Auditable<UUID> {

    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false)
    private String name;

}

package com.archmotion.repository;

import com.archmotion.entity.Diagram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DiagramRepository extends JpaRepository<Diagram, Long> {

    Page<Diagram> findAllByUserUuid(UUID userUuid, Pageable pageable);

    Optional<Diagram> findByUuid(UUID uuid);

}

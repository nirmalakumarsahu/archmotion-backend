package com.archmotion.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record DiagramResponse(
        UUID uuid,
        String name,
        String diagramData,
        Instant createdAt
) {
}

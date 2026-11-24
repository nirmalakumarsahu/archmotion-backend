package com.archmotion.service;

import com.archmotion.dto.request.DiagramRequest;
import com.archmotion.dto.response.DiagramResponse;
import com.archmotion.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DiagramService {
    DiagramResponse create(DiagramRequest diagramRequest);

    PageResponse<DiagramResponse> getAll(Pageable pageable);

    void delete(UUID uuid);

    DiagramResponse update(UUID uuid, DiagramRequest diagramRequest);
}

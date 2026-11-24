package com.archmotion.service.impl;

import com.archmotion.dto.request.DiagramRequest;
import com.archmotion.dto.response.DiagramResponse;
import com.archmotion.dto.response.PageResponse;
import com.archmotion.entity.Diagram;
import com.archmotion.entity.User;
import com.archmotion.exception.custom.ResourceNotFoundException;
import com.archmotion.repository.DiagramRepository;
import com.archmotion.repository.UserRepository;
import com.archmotion.security.dto.CustomUserPrincipal;
import com.archmotion.security.util.SecurityUtil;
import com.archmotion.service.DiagramService;
import com.archmotion.service.mapper.DiagramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiagramServiceImpl implements DiagramService {

    private final DiagramRepository diagramRepository;
    private final UserRepository userRepository;

    @Override
    public DiagramResponse create(DiagramRequest diagramRequest) {
        UUID currentUserUuid = SecurityUtil.getCurrentUserUuid();
        User user = userRepository.findByUuid(currentUserUuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        Diagram diagram = Diagram.builder()
                .name(diagramRequest.name())
                .diagramData(diagramRequest.diagramData())
                .user(user)
                .build();
        diagram = diagramRepository.save(diagram);
        return DiagramMapper.toResponse(diagram);
    }

    @Override
    public PageResponse<DiagramResponse> getAll(Pageable pageable) {
        CustomUserPrincipal user = SecurityUtil.getCurrentUser();
        Page<Diagram> diagramResponsePage = diagramRepository.findAllByUserUuid(user.getUserUuid(), pageable);

        return PageResponse.from(
                diagramResponsePage.map(DiagramMapper::toResponse)
        );
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        Diagram diagram = getDiagram(uuid);
        diagram.markDeleted(SecurityUtil.getCurrentUserUuid());
    }

    @Override
    @Transactional
    public DiagramResponse update(UUID uuid, DiagramRequest diagramRequest) {
        Diagram diagram = getDiagram(uuid);
        diagram.setName(diagramRequest.name());
        diagram.setDiagramData(diagramRequest.diagramData());

        return DiagramMapper.toResponse(diagram);
    }

    public Diagram getDiagram(UUID uuid) {
        return diagramRepository.findByUuid(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Diagram not Found"));
    }

}

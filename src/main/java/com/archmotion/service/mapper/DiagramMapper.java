package com.archmotion.service.mapper;

import com.archmotion.dto.response.DiagramResponse;
import com.archmotion.entity.Diagram;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiagramMapper {

    public DiagramResponse toResponse(Diagram diagram) {
        return DiagramResponse.builder()
                .uuid(diagram.getUuid())
                .name(diagram.getName())
                .diagramData(diagram.getDiagramData())
                .createdAt(diagram.getCreatedAt())
                .build();
    }

}

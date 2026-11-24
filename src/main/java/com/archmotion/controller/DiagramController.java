package com.archmotion.controller;

import com.archmotion.dto.request.DiagramRequest;
import com.archmotion.dto.response.ApiResponse;
import com.archmotion.dto.response.DiagramResponse;
import com.archmotion.dto.response.PageResponse;
import com.archmotion.service.DiagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/diagrams")
@RequiredArgsConstructor
public class DiagramController {

    private final DiagramService diagramService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<DiagramResponse> create(@RequestBody DiagramRequest diagramRequest) {
        return ApiResponse.success("Diagram has been saved", diagramService.create(diagramRequest));
    }

    @GetMapping
    public ApiResponse<PageResponse<DiagramResponse>> getAll(
            @PageableDefault(
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable) {
        PageResponse<DiagramResponse> diagramPageResponse =
                diagramService.getAll(pageable);
        return ApiResponse.success(diagramPageResponse.pagination().totalElements() == 0L ?
                        "No Diagram" : "Diagrams are retrieved successfully",
                diagramPageResponse
        );
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid) {
        diagramService.delete(uuid);
    }

    @PutMapping("/{uuid}")
    public ApiResponse<DiagramResponse> update(@PathVariable UUID uuid,
                                               @RequestBody DiagramRequest diagramRequest) {
        return ApiResponse.success("Diagram has been updated",
                diagramService.update(uuid, diagramRequest)
        );
    }

}

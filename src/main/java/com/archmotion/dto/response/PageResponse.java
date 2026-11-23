package com.archmotion.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PageResponse<T>(
        List<T> content,
        Pagination pagination
) {

    public static <T> PageResponse<T> from(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .pagination(
                        Pagination.builder()
                                .page(page.getNumber() + 1)
                                .size(page.getSize())
                                .totalElements(page.getTotalElements())
                                .totalPage(page.getTotalPages())
                                .first(page.isFirst())
                                .last(page.isLast())
                                .build()
                )
                .build();
    }

}

package com.archmotion.dto.response;

import lombok.Builder;

@Builder
public record Pagination(
        int page,
        int size,
        long totalElements,
        int totalPage,
        boolean first,
        boolean last
) {
}

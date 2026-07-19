package com.corebankingplatform.server.dto.response.pagination;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericPaginationResponse<T> {
    private List<T> items;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;
    private int pageNumber;

    private boolean hasNext;

    private boolean hasPrevious;
    private int pageSize;
    public static <T> GenericPaginationResponse<T> from(
            Page<?> page,
            List<T> items
    ) {

        return GenericPaginationResponse.<T>builder()
                .items(items)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .build();
    }

}

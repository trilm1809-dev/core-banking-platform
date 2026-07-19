package com.corebankingplatform.server.dto.response.pagination;

import org.springframework.data.domain.Page;

import java.util.List;

public final class PaginationMapper {

    private PaginationMapper() {}

    public static <T> GenericPaginationResponse<T> from(Page<?> page, List<T> items ) {

        GenericPaginationResponse<T> response = new GenericPaginationResponse<>();

        response.setItems(items);
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setHasNext(page.hasNext());
        response.setHasPrevious(page.hasPrevious());

        return response;
    }
}

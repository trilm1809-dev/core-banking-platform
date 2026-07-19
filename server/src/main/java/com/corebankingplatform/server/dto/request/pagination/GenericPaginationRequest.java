package com.corebankingplatform.server.dto.request.pagination;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GenericPaginationRequest {
    private int pageSize = 10;
    private int pageNumber = 0;
    private String searchKey;

    public Pageable toPageable() {
        return PageRequest.of(pageNumber, pageSize);
    }
}

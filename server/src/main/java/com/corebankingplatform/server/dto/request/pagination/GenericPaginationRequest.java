package com.corebankingplatform.server.dto.request.pagination;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class GenericPaginationRequest {
    private byte pageSize;
    private String searchKey;
    private byte pageNumber;
}

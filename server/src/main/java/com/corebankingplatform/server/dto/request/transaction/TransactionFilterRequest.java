package com.corebankingplatform.server.dto.request.transaction;

import com.corebankingplatform.server.dto.request.pagination.GenericPaginationRequest;
import com.corebankingplatform.server.enums.TransactionStatus;
import com.corebankingplatform.server.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionFilterRequest extends GenericPaginationRequest {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate toDate;

    private TransactionType transactionType;
    private TransactionStatus status;
    private Long accountId;
}

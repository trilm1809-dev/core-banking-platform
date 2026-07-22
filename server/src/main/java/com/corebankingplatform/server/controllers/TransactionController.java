package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.transaction.TransactionFilterRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.dto.response.transaction.TransactionResponse;
import com.corebankingplatform.server.services.interfaces.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping
    public ResponseEntity<GenericResponse<GenericPaginationResponse<TransactionResponse>>> getTransactions(
            TransactionFilterRequest request
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(transactionService.getTransactions(request))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<TransactionResponse>> getTransactionDetail(
            @PathVariable long id
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(transactionService.getTransactionDetail(id))
        );
    }
}

package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.transfer.CreateTransferRequest;
import com.corebankingplatform.server.dto.request.transfer.UpdateTransferStatusRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.transfer.TransferResponse;
import com.corebankingplatform.server.services.interfaces.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final ITransferService transferService;

    @PostMapping
    public ResponseEntity<GenericResponse<TransferResponse>> createTransfer(
            @RequestBody CreateTransferRequest request
    ) {
        TransferResponse response = transferService.createTransfer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(response));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<GenericResponse<TransferResponse>> getTransferDetail(
            @PathVariable long transactionId
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(transferService.getTransferDetail(transactionId))
        );
    }

    @PatchMapping("/{transferId}/status")
    public ResponseEntity<GenericResponse<TransferResponse>> updateTransferStatus(
            @PathVariable long transferId,
            @RequestBody UpdateTransferStatusRequest request
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(transferService.updateTransferStatus(transferId, request))
        );
    }
}


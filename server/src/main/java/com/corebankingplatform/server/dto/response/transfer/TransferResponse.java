package com.corebankingplatform.server.dto.response.transfer;

import com.corebankingplatform.server.entites.Transfer;
import com.corebankingplatform.server.enums.TransferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransferResponse {

    private long transactionId;
    private TransferStatus status;
    private LocalDateTime createdAt;
    private long sourceAccountId;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private String description;

    public static TransferResponse from(Transfer transfer) {
        return TransferResponse.builder()
                .transactionId(transfer.getId())
                .status(transfer.getStatus())
                .createdAt(transfer.getCreateDate())
                .sourceAccountId(transfer.getFromAccount().getId())
                .destinationAccountNumber(transfer.getToAccount().getAccountNumber())
                .amount(transfer.getAmount())
                .description(transfer.getDescription())
                .build();
    }
}

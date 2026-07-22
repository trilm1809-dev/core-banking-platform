package com.corebankingplatform.server.dto.response.transaction;

import com.corebankingplatform.server.entites.Transaction;
import com.corebankingplatform.server.enums.TransactionStatus;
import com.corebankingplatform.server.enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionResponse {

    private long id;
    private long accountId;
    private String accountNumber;
    private Long transferId;
    private TransactionType transactionType;
    private TransactionStatus status;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String description;
    private LocalDateTime createdAt;

    public  TransactionResponse(Transaction transaction) {
         TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .accountNumber(transaction.getAccount().getAccountNumber())
                .transferId(transaction.getTransfer() != null ? transaction.getTransfer().getId() : null)
                .transactionType(transaction.getType())
                .status(transaction.getStatus())
                .amount(transaction.getAmount())
                .balanceAfter(transaction.getBalanceAfter())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreateDate())
                .build();
    }
}

package com.corebankingplatform.server.dto.request.transfer;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateTransferRequest {

    private long sourceAccountId;
    private String destinationAccountNumber;
    private BigDecimal amount;
    private String description;
}

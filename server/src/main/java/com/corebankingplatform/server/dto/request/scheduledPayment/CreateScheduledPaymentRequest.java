package com.corebankingplatform.server.dto.request.scheduledPayment;

import com.corebankingplatform.server.enums.PaymentFrequency;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateScheduledPaymentRequest {

    private Long sourceAccountId;

    private Long beneficiaryId;

    private BigDecimal amount;

    private String description;

    private PaymentFrequency frequency;

    private LocalDate nextExecutionDate;

    private LocalDate endDate;
}

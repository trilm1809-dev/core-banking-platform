package com.corebankingplatform.server.dto.response.scheduledPayment;

import com.corebankingplatform.server.entites.ScheduledPayment;
import com.corebankingplatform.server.enums.PaymentFrequency;
import com.corebankingplatform.server.enums.ScheduledPaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class ScheduledPaymentResponse {

    private Long id;

    private Long sourceAccountId;

    private Long beneficiaryId;

    private BigDecimal amount;

    private String description;

    private PaymentFrequency frequency;

    private LocalDate nextExecutionDate;

    private LocalDate endDate;

    private ScheduledPaymentStatus status;

    public static ScheduledPaymentResponse from(ScheduledPayment payment
    ) {
        return ScheduledPaymentResponse
                .builder()
                .id(payment.getId())
                .sourceAccountId(
                        payment.getSourceAccount()
                                .getId())
                .beneficiaryId(
                        payment.getBeneficiary()
                                .getId())
                .amount(payment.getAmount())
                .description(
                        payment.getDescription())
                .frequency(
                        payment.getFrequency())
                .nextExecutionDate(
                        payment.getNextExecutionDate())
                .endDate(
                        payment.getEndDate())
                .status(
                        payment.getStatus())
                .build();
    }
}

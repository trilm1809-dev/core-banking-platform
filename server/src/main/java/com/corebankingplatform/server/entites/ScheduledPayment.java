package com.corebankingplatform.server.entites;

import com.corebankingplatform.server.enums.PaymentFrequency;
import com.corebankingplatform.server.enums.ScheduledPaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "scheduled_payments")
public class ScheduledPayment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private BankAccount sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id")
    private Beneficiary beneficiary;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private PaymentFrequency frequency;

    private LocalDate nextExecutionDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ScheduledPaymentStatus status;
}

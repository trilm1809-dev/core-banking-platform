package com.corebankingplatform.server.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "fraud_alerts")
public class FraudAlert extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    private FraudRuleType ruleType;

    @Enumerated(EnumType.STRING)
    private FraudRiskLevel riskLevel;

    private String description;
}

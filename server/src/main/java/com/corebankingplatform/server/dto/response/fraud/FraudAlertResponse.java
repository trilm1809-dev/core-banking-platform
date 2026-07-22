package com.corebankingplatform.server.dto.response.fraud;

import com.corebankingplatform.server.enums.FraudRiskLevel;
import com.corebankingplatform.server.enums.FraudRuleType;

import java.time.LocalDateTime;

public class FraudAlertResponse {

    private Long id;

    private Long transactionId;

    private FraudRuleType ruleType;

    private FraudRiskLevel riskLevel;

    private String description;

    private LocalDateTime createdAt;
}

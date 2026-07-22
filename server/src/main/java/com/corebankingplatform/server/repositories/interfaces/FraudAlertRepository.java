package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.FraudAlert;
import com.corebankingplatform.server.enums.FraudRiskLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudAlertRepository
        extends JpaRepository<
                FraudAlert,
                Long> {

    Page<FraudAlert>
    findAll(
            Specification<FraudAlert> spec,
            Pageable pageable
    );

    long countByRiskLevel(
            FraudRiskLevel riskLevel
    );
}

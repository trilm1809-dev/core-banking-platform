package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.ScheduledPayment;
import com.corebankingplatform.server.enums.ScheduledPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduledPaymentRepository extends JpaRepository<ScheduledPayment, Long> {
    List<ScheduledPayment>
    findByStatusAndNextExecutionDateLessThanEqual(ScheduledPaymentStatus status, LocalDate date);
}

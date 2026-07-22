package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}

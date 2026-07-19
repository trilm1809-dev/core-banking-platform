package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID>, JpaSpecificationExecutor<BankAccount> {
    Optional<BankAccount> findById(long id);
}

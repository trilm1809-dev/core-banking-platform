package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>, JpaSpecificationExecutor<BankAccount> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);
}

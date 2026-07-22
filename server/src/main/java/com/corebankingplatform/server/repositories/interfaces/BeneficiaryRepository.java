package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.Beneficiary;
import com.corebankingplatform.server.enums.BeneficiaryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    List<Beneficiary> findByCustomerIdAndStatus(long customerId, BeneficiaryStatus status);

    Optional<Beneficiary> findByIdAndCustomerId(long id, long customerId);

    boolean existsByCustomerIdAndAccountNumberAndStatus(long customerId, String accountNumber, BeneficiaryStatus status);
}

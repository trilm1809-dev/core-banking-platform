package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

package com.corebankingplatform.server.repositories.interfaces;

import com.corebankingplatform.server.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerBy(int customerId);
    Boolean deleteCustomerBy(int customerId);
    
}

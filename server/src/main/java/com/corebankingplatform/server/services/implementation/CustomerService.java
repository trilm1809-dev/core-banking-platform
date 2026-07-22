package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.services.interfaces.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Override
    public ResponseEntity<GenericResponse<Boolean>> deleteCustomer(int customerId) {
        return ResponseEntity.ok(GenericResponse.success(true));
    }
}

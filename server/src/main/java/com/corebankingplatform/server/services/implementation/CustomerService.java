package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.services.interfaces.ICustomerService;
import org.springframework.http.ResponseEntity;

public class CustomerService extends ICustomerService {
    @Override
    public ResponseEntity<GenericResponse<Boolean>> deleteCustomer(int customerId) {
        return null;
    }
}

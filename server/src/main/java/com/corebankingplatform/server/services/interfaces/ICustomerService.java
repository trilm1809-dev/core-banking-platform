package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.response.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    ResponseEntity<GenericResponse<Boolean>> deleteCustomer(int customerId);
}

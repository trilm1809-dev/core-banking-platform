package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.auth.SignInRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface ICustomerService {
    public ResponseEntity<GenericResponse<Boolean>> deleteCustomer(int customerId);
}

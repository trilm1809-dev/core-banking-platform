package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.customer.CustomerSearchRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.customer.CustomerDetailResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@RequestMapping("customer")
public class CustomerController {

    @GetMapping
    public ResponseEntity<GenericResponse<GenericPaginationResponse<>>> getCustomers(CustomerSearchRequest request) {
        //
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<GenericResponse<CustomerDetailResponse>> getCustomer(@PathVariable int customerId) {

        return ResponseEntity.ok(
                GenericResponse.success(customer)
        );
    }

    @PostMapping
    public ResponseEntity<GenericResponse<CustomerDetailResponse>> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request
    ) {

        CustomerDetailResponse customer =
                customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(customer));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<GenericResponse<CustomerDetailResponse>> updateCustomer(
            @PathVariable UUID customerId,
            @Valid @RequestBody UpdateCustomerRequest request
    ) {


        return ResponseEntity.ok(
                GenericResponse.success(customer)
        );
    }


    @PatchMapping("/{customerId}/status")
    public ResponseEntity<GenericResponse<Void>> updateStatus(
            @PathVariable UUID customerId,
            @RequestParam boolean active
    ) {

        return ResponseEntity.ok(
                GenericResponse.success(null)
        );
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }

}

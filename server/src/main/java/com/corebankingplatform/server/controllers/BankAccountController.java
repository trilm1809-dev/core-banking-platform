package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.bankAccount.BankAccountSearchRequest;
import com.corebankingplatform.server.dto.request.bankAccount.CreationalBankAccountRequest;
import com.corebankingplatform.server.dto.request.bankAccount.UpdateBankAccountRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountsResponse;
import com.corebankingplatform.server.services.interfaces.IBankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final IBankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<GenericResponse<GenericPaginationResponse<BankAccountsResponse>>> getAccounts(
            BankAccountSearchRequest request
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(bankAccountService.getAccounts(request))
        );
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<GenericResponse<BankAccountResponse>> getAccountDetail(
            @PathVariable long accountId
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(bankAccountService.getAccountDetail(accountId))
        );
    }

    @PostMapping
    public ResponseEntity<GenericResponse<BankAccountResponse>> createAccount(
            @RequestBody CreationalBankAccountRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(bankAccountService.createAccount(request)));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<GenericResponse<BankAccountResponse>> updateAccount(
            @PathVariable long accountId,
            @RequestBody UpdateBankAccountRequest request
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(bankAccountService.updateAccount(accountId, request))
        );
    }

    @PatchMapping("/{accountId}/close")
    public ResponseEntity<GenericResponse<Boolean>> closeAccount(@PathVariable long accountId) {
        bankAccountService.closeAccount(accountId);
        return ResponseEntity.ok(GenericResponse.success(true));
    }

    @PatchMapping("/{accountId}/activate")
    public ResponseEntity<GenericResponse<Boolean>> activateAccount(@PathVariable long accountId) {
        bankAccountService.activateAccount(accountId);
        return ResponseEntity.ok(GenericResponse.success(true));
    }

    @PatchMapping("/{accountId}/deactivate")
    public ResponseEntity<GenericResponse<Boolean>> deactivateAccount(@PathVariable long accountId) {
        bankAccountService.deactivateAccount(accountId);
        return ResponseEntity.ok(GenericResponse.success(true));
    }
}

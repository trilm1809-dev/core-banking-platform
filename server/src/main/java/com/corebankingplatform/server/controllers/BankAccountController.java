package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.bankAccount.BankAccountSearchRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.repositories.interfaces.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;

    @GetMapping
    public ResponseEntity<GenericResponse<GenericPaginationResponse<BankAccountSummaryResponse>> getAccounts(BankAccountSearchRequest request,                                                                                                             Pageable pageable
    ) {

        return ResponseEntity.ok(
                GenericResponse.success(
                        bankAccountService.getAccounts(
                                request
                        )
                )
        );
    }

    /**
     * Detail
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<
            GenericResponse<BankAccountDetailResponse>
            > getAccountDetail(
            @PathVariable UUID accountId
    ) {

        return ResponseEntity.ok(
                GenericResponse.success(
                        bankAccountService.getAccountDetail(
                                accountId
                        )
                )
        );
    }

    @PostMapping
    public ResponseEntity<GenericResponse<BankAccountDetailResponse>>
    createAccount(
            @Valid @RequestBody CreateBankAccountRequest request
    ) {

        return ResponseEntity.ok(
                GenericResponse.success(
                        bankAccountService.createAccount(request)
                )
        );
    }

    /**
     * Search + Pagination
     */


    /**
     * Update
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<
            GenericResponse<BankAccountDetailResponse>
            > updateAccount(
            @PathVariable UUID accountId,
            @Valid @RequestBody UpdateBankAccountRequest request
    ) {

        return ResponseEntity.ok(
                GenericResponse.success(
                        bankAccountService.updateAccount(
                                accountId,
                                request
                        )
                )
        );
    }

    /**
     * Close Account
     */
    @PatchMapping("/{accountId}/close")
    public ResponseEntity<
            GenericResponse<Boolean>
            > closeAccount(
            @PathVariable UUID accountId
    ) {

        bankAccountService.closeAccount(accountId);

        return ResponseEntity.ok(
                GenericResponse.success(true)
        );
    }

    /**
     * Activate Account
     */
    @PatchMapping("/{accountId}/activate")
    public ResponseEntity<
            GenericResponse<Boolean>
            > activateAccount(
            @PathVariable UUID accountId
    ) {

        bankAccountService.activateAccount(accountId);

        return ResponseEntity.ok(
                GenericResponse.success(true)
        );
    }

    /**
     * Deactivate Account
     */
    @PatchMapping("/{accountId}/deactivate")
    public ResponseEntity<
            GenericResponse<Boolean>
            > deactivateAccount(
            @PathVariable UUID accountId
    ) {

        bankAccountService.deactivateAccount(accountId);

        return ResponseEntity.ok(
                GenericResponse.success(true)
        );
    }


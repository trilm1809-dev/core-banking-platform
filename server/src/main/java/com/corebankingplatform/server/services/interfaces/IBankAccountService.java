package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.bankAccount.BankAccountSearchRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccounts;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;

import java.util.UUID;

public interface IBankAccountService {
    GenericResponse<GenericPaginationResponse<BankAccounts>> getAccounts(BankAccountSearchRequest request);

    GenericResponse<BankAccountResponse> getAccount(long accountId);
//    GenericResponse<BankAccountDetailResponse> createAccount(CreateBankAccountRequest request);
    GenericResponse<BankAccountResponse> updateAccount(long accountId,UpdateBankAccountRequest request
    );
    void deleteAccount(
            UUID accountId
    );
}

package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.bankAccount.BankAccountSearchRequest;
import com.corebankingplatform.server.dto.request.bankAccount.CreationalBankAccountRequest;
import com.corebankingplatform.server.dto.request.bankAccount.UpdateBankAccountRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountsResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;

public interface IBankAccountService {

    GenericResponse<GenericPaginationResponse<BankAccountsResponse>> getAccounts(BankAccountSearchRequest request);

    GenericResponse<BankAccountResponse> getAccountDetail(long accountId);

    GenericResponse<BankAccountResponse> createAccount(CreationalBankAccountRequest request);

    GenericResponse<BankAccountResponse> updateAccount(long accountId, UpdateBankAccountRequest request);

    void closeAccount(long accountId);

    void activateAccount(long accountId);

    void deactivateAccount(long accountId);
}

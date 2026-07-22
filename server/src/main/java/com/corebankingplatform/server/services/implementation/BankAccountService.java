package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.bankAccount.BankAccountSearchRequest;
import com.corebankingplatform.server.dto.request.bankAccount.CreationalBankAccountRequest;
import com.corebankingplatform.server.dto.request.bankAccount.UpdateBankAccountRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountsResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.entites.BankAccount;
import com.corebankingplatform.server.enums.AccountStatus;
import com.corebankingplatform.server.enums.AccountType;
import com.corebankingplatform.server.exception.BusinessException;
import com.corebankingplatform.server.repositories.interfaces.BankAccountRepository;
import com.corebankingplatform.server.services.interfaces.IBankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService implements IBankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public GenericResponse<GenericPaginationResponse<BankAccountsResponse>> getAccounts(BankAccountSearchRequest request) {
        Page<BankAccount> page = bankAccountRepository.findAll(request.toPageable());
        List<BankAccountsResponse> items = page.getContent().stream().map(BankAccountsResponse::new).toList();
        return GenericResponse.success(GenericPaginationResponse.from(page, items));
    }

    @Override
    public GenericResponse<BankAccountResponse> getAccountDetail(long accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));
        return GenericResponse.success(new BankAccountResponse(bankAccount));
    }

    @Override
    public GenericResponse<BankAccountResponse> createAccount(CreationalBankAccountRequest request) {
        BankAccount bankAccount = BankAccount.builder()
                .accountNumber(request.getAccountNumber())
                .accountName(request.getAccountName())
                .status(request.getStatus() != null ? request.getStatus() : AccountStatus.ACTIVE)
                .accountType(AccountType.SAVINGS)
                .build();
        return GenericResponse.success(new BankAccountResponse(bankAccountRepository.save(bankAccount)));
    }

    @Override
    public GenericResponse<BankAccountResponse> updateAccount(long accountId, UpdateBankAccountRequest request) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));
        if (request.getAccountName() != null) {
            bankAccount.setAccountName(request.getAccountName());
        }
        BankAccountResponse bankAccountResponse =  new BankAccountResponse(bankAccountRepository.save(bankAccount));

        return GenericResponse.success(bankAccountResponse);
    }

    @Override
    public void closeAccount(long accountId) {
        updateStatus(accountId, AccountStatus.CLOSED);
    }

    @Override
    public void activateAccount(long accountId) {
        updateStatus(accountId, AccountStatus.ACTIVE);
    }

    @Override
    public void deactivateAccount(long accountId) {
        updateStatus(accountId, AccountStatus.BLOCKED);
    }

    private void updateStatus(long accountId, AccountStatus status) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account not found", HttpStatus.NOT_FOUND));
        bankAccount.setStatus(status);
        bankAccountRepository.save(bankAccount);
    }
}

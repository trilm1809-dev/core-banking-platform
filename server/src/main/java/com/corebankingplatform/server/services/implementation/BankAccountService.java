package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.bankAccount.BankAccountSearchRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccountResponse;
import com.corebankingplatform.server.dto.response.bankAccount.BankAccounts;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.entites.BankAccount;
import com.corebankingplatform.server.repositories.interfaces.BankAccountRepository;
import com.corebankingplatform.server.services.interfaces.IBankAccountService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public class BankAccountService implements IBankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public GenericResponse<GenericPaginationResponse<BankAccounts>> getAccounts(BankAccountSearchRequest request) {
        Page<BankAccount> page =  bankAccountRepository.findAll(request.toPageable());

        List<BankAccounts> items = page.getContent().stream().map(BankAccounts::new).toList();

        return GenericResponse.success(GenericPaginationResponse.from(page, items));
    }

    @Override
    public BankAccountDetailResponse createAccount(CreateBankAccountRequest request) {
        return null;
    }

    @Override
    public GenericResponse<BankAccountResponse> getAccount(long accountId) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(accountId);
        BankAccountResponse response = new BankAccountResponse(bankAccount.get());
        return GenericResponse.success(response);
    }

    @Override
    public BankAccountDetailResponse updateAccount(UUID accountId, UpdateBankAccountRequest request) {
        return null;
    }

    @Override
    public void closeAccount(UUID accountId) {

    }

    @Override
    public void activateAccount(UUID accountId) {

    }

    @Override
    public void deactivateAccount(UUID accountId) {

    }
}

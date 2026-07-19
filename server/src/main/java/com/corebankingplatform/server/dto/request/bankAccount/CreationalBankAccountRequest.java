package com.corebankingplatform.server.dto.request.bankAccount;

import com.corebankingplatform.server.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreationalBankAccountRequest {
    private String accountNumber;

    private String accountName;

    private AccountStatus status;
}

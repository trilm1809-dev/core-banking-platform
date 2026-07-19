package com.corebankingplatform.server.dto.response.bankAccount;

import com.corebankingplatform.server.entites.BankAccount;
import com.corebankingplatform.server.enums.AccountStatus;
import com.corebankingplatform.server.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountResponse {
    private long id;
    private String accountNumber;

    private String accountName;


    private BigDecimal balance;

    private AccountType accountType;

    private AccountStatus status;

    public BankAccountResponse(BankAccount entity) {

        this.id = entity.getId();
        this.accountNumber =
                entity.getAccountNumber();
        this.accountName =
                entity.getAccountName();
        this.balance =
                entity.getBalance();
        this.accountType =
                entity.getAccountType();
        this.status =
                entity.getStatus();
    }
}

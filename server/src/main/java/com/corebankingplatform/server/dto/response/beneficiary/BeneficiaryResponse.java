package com.corebankingplatform.server.dto.response.beneficiary;

import com.corebankingplatform.server.entites.Beneficiary;
import com.corebankingplatform.server.enums.BeneficiaryStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BeneficiaryResponse {

    private long id;
    private long customerId;
    private String bankName;
    private String accountNumber;
    private String accountHolderName;
    private String nickname;
    private BeneficiaryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BeneficiaryResponse (Beneficiary beneficiary) {
        BeneficiaryResponse.builder()
                .id(beneficiary.getId())
                .customerId(beneficiary.getCustomer().getId())
                .bankName(beneficiary.getBankName())
                .accountNumber(beneficiary.getAccountNumber())
                .accountHolderName(beneficiary.getAccountHolderName())
                .nickname(beneficiary.getNickname())
                .status(beneficiary.getStatus())
                .createdAt(beneficiary.getCreateDate())
                .updatedAt(beneficiary.getLastUpdateDate())
                .build();
    }
}

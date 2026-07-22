package com.corebankingplatform.server.dto.request.beneficiary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBeneficiaryRequest {
    private String bankName;
    private String accountNumber;
    private String accountHolderName;
    private String nickname;
}

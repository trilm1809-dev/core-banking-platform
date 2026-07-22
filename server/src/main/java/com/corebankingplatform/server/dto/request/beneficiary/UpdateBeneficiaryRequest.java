package com.corebankingplatform.server.dto.request.beneficiary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBeneficiaryRequest {
    private String bankName;
    private String accountHolderName;
    private String nickname;
}

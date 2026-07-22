package com.corebankingplatform.server.dto.request.transfer;

import com.corebankingplatform.server.enums.TransferStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTransferStatusRequest {

    private TransferStatus status;
}

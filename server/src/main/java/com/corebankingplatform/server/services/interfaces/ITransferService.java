package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.transfer.CreateTransferRequest;
import com.corebankingplatform.server.dto.request.transfer.UpdateTransferStatusRequest;
import com.corebankingplatform.server.dto.response.transfer.TransferResponse;

public interface ITransferService {

    TransferResponse createTransfer(CreateTransferRequest request);

    TransferResponse getTransferDetail(long transactionId);

    TransferResponse updateTransferStatus(long transferId, UpdateTransferStatusRequest request);
}

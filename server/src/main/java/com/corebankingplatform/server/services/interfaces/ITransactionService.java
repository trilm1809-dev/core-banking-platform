package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.transaction.TransactionFilterRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.dto.response.transaction.TransactionResponse;

public interface ITransactionService {

    GenericResponse<GenericPaginationResponse<TransactionResponse>> getTransactions(TransactionFilterRequest request);

    GenericResponse<TransactionResponse> getTransactionDetail(long id);
}

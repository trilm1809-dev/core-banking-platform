package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.transfer.CreateTransferRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.fraud.FraudAlertResponse;
import com.corebankingplatform.server.dto.response.fraud.FraudStatisticsResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.entites.Beneficiary;

public interface FraudDetectionService {

    void evaluateTransfer(
            CreateTransferRequest request,
            Beneficiary beneficiary
    );

    GenericResponse<
                GenericPaginationResponse<
                                    FraudAlertResponse>>
    getFraudAlerts(
            FraudAlertSearchRequest request
    );

    GenericResponse<FraudAlertResponse>
    getFraudAlert(
            Long id
    );

    GenericResponse<FraudStatisticsResponse>
    getStatistics();
}

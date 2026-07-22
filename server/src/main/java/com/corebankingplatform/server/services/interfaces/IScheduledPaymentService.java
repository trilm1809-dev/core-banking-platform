package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.scheduledPayment.CreateScheduledPaymentRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.dto.response.scheduledPayment.ScheduledPaymentResponse;

public interface IScheduledPaymentService {
    GenericResponse<ScheduledPaymentResponse> createScheduledPayment( CreateScheduledPaymentRequest request);

    GenericResponse<ScheduledPaymentResponse> updateScheduledPayment( Long id, UpdateScheduledPaymentRequest request);

    void cancelScheduledPayment(Long id);

    void pauseScheduledPayment(Long id);

    void resumeScheduledPayment(Long id);

    GenericResponse<GenericPaginationResponse<ScheduledPaymentResponse>> getScheduledPayments(ScheduledPaymentSearchRequest request);

    void processDuePayments();
}

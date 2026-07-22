package com.corebankingplatform.server.backgroundJobs;

import com.corebankingplatform.server.services.interfaces.IScheduledPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledPaymentJob {

    private final IScheduledPaymentService scheduledPaymentService;

    @Scheduled(cron = "0 */5 * * * *")
    public void execute() {

        scheduledPaymentService
                .processDuePayments();
    }
}
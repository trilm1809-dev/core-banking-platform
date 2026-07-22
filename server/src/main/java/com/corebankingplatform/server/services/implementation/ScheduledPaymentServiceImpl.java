package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.scheduledPayment.CreateScheduledPaymentRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.dto.response.scheduledPayment.ScheduledPaymentResponse;
import com.corebankingplatform.server.entites.BankAccount;
import com.corebankingplatform.server.entites.Beneficiary;
import com.corebankingplatform.server.entites.ScheduledPayment;
import com.corebankingplatform.server.enums.ScheduledPaymentStatus;
import com.corebankingplatform.server.repositories.interfaces.BankAccountRepository;
import com.corebankingplatform.server.repositories.interfaces.BeneficiaryRepository;
import com.corebankingplatform.server.repositories.interfaces.ScheduledPaymentRepository;
import com.corebankingplatform.server.services.interfaces.IScheduledPaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduledPaymentServiceImpl implements IScheduledPaymentService {
    private final ScheduledPaymentRepository scheduledPaymentRepository;

    private final BankAccountRepository bankAccountRepository;

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public GenericResponse<ScheduledPaymentResponse> createScheduledPayment(CreateScheduledPaymentRequest request) {

        BankAccount account = bankAccountRepository.findById(
                                request.getSourceAccountId()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Account not found"
                                )
                        );

        Beneficiary beneficiary =
                beneficiaryRepository
                        .findById(
                                request.getBeneficiaryId()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Beneficiary not found"
                                )
                        );

        validateCreateRequest(
                request,
                account,
                beneficiary
        );

        ScheduledPayment payment =
                ScheduledPayment.builder()
                        .sourceAccount(account)
                        .beneficiary(
                                beneficiary
                        )
                        .amount(
                                request.getAmount()
                        )
                        .description(
                                request.getDescription()
                        )
                        .frequency(
                                request.getFrequency()
                        )
                        .nextExecutionDate(
                                request.getNextExecutionDate()
                        )
                        .endDate(
                                request.getEndDate()
                        )
                        .status(
                                ScheduledPaymentStatus.ACTIVE
                        )
                        .build();

        scheduledPaymentRepository
                .save(payment);

        return GenericResponse.success(
                ScheduledPaymentResponse
                        .from(payment)
        );
    }

    private void validateCreateRequest(
            CreateScheduledPaymentRequest request,
            BankAccount account,
            Beneficiary beneficiary
    ) {

        if (request.getAmount()
                .compareTo(
                        BigDecimal.ZERO
                ) <= 0) {

            throw new RuntimeException(
                    "Amount must be greater than zero"
            );
        }

        if (
                request.getNextExecutionDate()
                        .isBefore(
                                LocalDate.now()
                        )
        ) {

            throw new RuntimeException(
                    "Execution date cannot be in the past"
            );
        }

        if (
                request.getEndDate() != null
                        &&
                        request.getEndDate()
                                .isBefore(
                                        request.getNextExecutionDate()
                                )
        ) {

            throw new RuntimeException(
                    "End date is invalid"
            );
        }
    }

    @Override
    public GenericResponse<ScheduledPaymentResponse> updateScheduledPayment(Long id, UpdateScheduledPaymentRequest request) {
        return null;
    }

    @Override
    public void cancelScheduledPayment(Long id) {

    }

    @Override
    public void pauseScheduledPayment(Long id) {

    }

    @Override
    public void resumeScheduledPayment(Long id) {

    }

    @Override
    public GenericResponse<GenericPaginationResponse<ScheduledPaymentResponse>> getScheduledPayments(ScheduledPaymentSearchRequest request) {
        return null;
    }

    @Override
    public void processDuePayments() {

    }
}

package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.transfer.CreateTransferRequest;
import com.corebankingplatform.server.dto.request.transfer.UpdateTransferStatusRequest;
import com.corebankingplatform.server.dto.response.transfer.TransferResponse;
import com.corebankingplatform.server.entites.AuditLog;
import com.corebankingplatform.server.entites.BankAccount;
import com.corebankingplatform.server.entites.Customer;
import com.corebankingplatform.server.entites.Transaction;
import com.corebankingplatform.server.entites.Transfer;
import com.corebankingplatform.server.entites.User;
import com.corebankingplatform.server.enums.AccountStatus;
import com.corebankingplatform.server.enums.TransactionStatus;
import com.corebankingplatform.server.enums.TransactionType;
import com.corebankingplatform.server.enums.TransferStatus;
import com.corebankingplatform.server.exception.BusinessException;
import com.corebankingplatform.server.repositories.interfaces.AuditLogRepository;
import com.corebankingplatform.server.repositories.interfaces.BankAccountRepository;
import com.corebankingplatform.server.repositories.interfaces.TransferRepository;
import com.corebankingplatform.server.security.SecurityUtils;
import com.corebankingplatform.server.services.interfaces.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService implements ITransferService {

    private final BankAccountRepository bankAccountRepository;
    private final TransferRepository transferRepository;
    private final AuditLogRepository auditLogRepository;

    @Override
    @Transactional
    public TransferResponse createTransfer(CreateTransferRequest request) {
        validateTransferRequest(request);

        Customer customer = SecurityUtils.getCurrentCustomer();
        BankAccount sourceAccount = validateSourceAccount(request.getSourceAccountId(), customer);
        BankAccount destinationAccount = validateDestinationAccount(request.getDestinationAccountNumber());
        checkBalance(sourceAccount, request.getAmount());

        Transfer transfer = Transfer.builder()
                .fromAccount(sourceAccount)
                .toAccount(destinationAccount)
                .amount(request.getAmount())
                .description(request.getDescription())
                .status(TransferStatus.SUCCESS)
                .build();

        debitAccount(sourceAccount, request.getAmount());
        creditAccount(destinationAccount, request.getAmount());

        Transaction debitTransaction = createTransaction(
                sourceAccount,
                transfer,
                TransactionType.DEBIT,
                request.getAmount(),
                sourceAccount.getBalance(),
                request.getDescription()
        );
        Transaction creditTransaction = createTransaction(
                destinationAccount,
                transfer,
                TransactionType.CREDIT,
                request.getAmount(),
                destinationAccount.getBalance(),
                request.getDescription()
        );

        transfer.addTransaction(debitTransaction);
        transfer.addTransaction(creditTransaction);

        Transfer savedTransfer = transferRepository.save(transfer);
        saveAuditLog(savedTransfer, SecurityUtils.getCurrentUser());

        return TransferResponse.from(savedTransfer);
    }

    @Override
    public TransferResponse getTransferDetail(long transactionId) {
        Customer customer = SecurityUtils.getCurrentCustomer();
        Transfer transfer = findOwnedTransfer(transactionId, customer);
        return TransferResponse.from(transfer);
    }

    @Override
    @Transactional
    public TransferResponse updateTransferStatus(long transferId, UpdateTransferStatusRequest request) {
        if (request.getStatus() == null) {
            throw new BusinessException("Status is required");
        }

        Customer customer = SecurityUtils.getCurrentCustomer();
        Transfer transfer = findOwnedTransfer(transferId, customer);

        transfer.setStatus(request.getStatus());

        TransactionStatus transactionStatus = mapTransferStatusToTransactionStatus(request.getStatus());
        for (Transaction transaction : transfer.getTransactions()) {
            transaction.setStatus(transactionStatus);
        }

        Transfer savedTransfer = transferRepository.save(transfer);
        saveAuditLog(savedTransfer, SecurityUtils.getCurrentUser(), "UPDATE_TRANSFER_STATUS",
                "Transfer status updated to " + request.getStatus());

        return TransferResponse.from(savedTransfer);
    }

    private Transfer findOwnedTransfer(long transferId, Customer customer) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new BusinessException("Transfer not found", HttpStatus.NOT_FOUND));

        if (transfer.getFromAccount().getCustomer().getId() != customer.getId()
                && transfer.getToAccount().getCustomer().getId() != customer.getId()) {
            throw new BusinessException("Transfer not found", HttpStatus.NOT_FOUND);
        }

        return transfer;
    }

    private TransactionStatus mapTransferStatusToTransactionStatus(TransferStatus transferStatus) {
        return switch (transferStatus) {
            case PENDING -> TransactionStatus.PENDING;
            case SUCCESS -> TransactionStatus.SUCCESS;
            case FAILED -> TransactionStatus.FAILED;
        };
    }

    private void validateTransferRequest(CreateTransferRequest request) {
        if (request.getSourceAccountId() <= 0) {
            throw new BusinessException("Source account is required");
        }
        if (request.getDestinationAccountNumber() == null || request.getDestinationAccountNumber().isBlank()) {
            throw new BusinessException("Destination account number is required");
        }
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Amount must be greater than zero");
        }
    }

    private BankAccount validateSourceAccount(long sourceAccountId, Customer customer) {
        BankAccount sourceAccount = bankAccountRepository.findById(sourceAccountId)
                .orElseThrow(() -> new BusinessException("Source account not found", HttpStatus.NOT_FOUND));

        if (sourceAccount.getCustomer().getId() != customer.getId()) {
            throw new BusinessException("Source account does not belong to current customer", HttpStatus.FORBIDDEN);
        }

        if (sourceAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new BusinessException("Source account is not active");
        }

        return sourceAccount;
    }

    private BankAccount validateDestinationAccount(String destinationAccountNumber) {
        BankAccount destinationAccount = bankAccountRepository.findByAccountNumber(destinationAccountNumber)
                .orElseThrow(() -> new BusinessException("Destination account not found", HttpStatus.NOT_FOUND));

        if (destinationAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new BusinessException("Destination account is not active");
        }

        return destinationAccount;
    }

    private void checkBalance(BankAccount sourceAccount, BigDecimal amount) {
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Insufficient balance");
        }
    }

    private void debitAccount(BankAccount account, BigDecimal amount) {
        account.setBalance(account.getBalance().subtract(amount));
        bankAccountRepository.save(account);
    }

    private void creditAccount(BankAccount account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        bankAccountRepository.save(account);
    }

    private Transaction createTransaction(
            BankAccount account,
            Transfer transfer,
            TransactionType type,
            BigDecimal amount,
            BigDecimal balanceAfter,
            String description
    ) {
        return Transaction.builder()
                .account(account)
                .transfer(transfer)
                .type(type)
                .status(TransactionStatus.SUCCESS)
                .amount(amount)
                .balanceAfter(balanceAfter)
                .description(description)
                .build();
    }

    private void saveAuditLog(Transfer transfer, User user) {
        saveAuditLog(transfer, user, "CREATE_TRANSFER",
                "Transfer from account " + transfer.getFromAccount().getAccountNumber()
                        + " to account " + transfer.getToAccount().getAccountNumber());
    }

    private void saveAuditLog(Transfer transfer, User user, String action, String details) {
        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .entityType("Transfer")
                .entityId(transfer.getId())
                .details(details)
                .performedByUserId(user.getId())
                .build();
        auditLogRepository.save(auditLog);
    }
}

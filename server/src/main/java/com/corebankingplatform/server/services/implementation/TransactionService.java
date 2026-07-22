package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.transaction.TransactionFilterRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.dto.response.transaction.TransactionResponse;
import com.corebankingplatform.server.entites.Customer;
import com.corebankingplatform.server.entites.Transaction;
import com.corebankingplatform.server.enums.TransactionStatus;
import com.corebankingplatform.server.enums.TransactionType;
import com.corebankingplatform.server.exception.BusinessException;
import com.corebankingplatform.server.repositories.interfaces.TransactionRepository;
import com.corebankingplatform.server.security.SecurityUtils;
import com.corebankingplatform.server.services.interfaces.ITransactionService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public GenericResponse<GenericPaginationResponse<TransactionResponse>> getTransactions(TransactionFilterRequest request) {
        Customer customer = SecurityUtils.getCurrentCustomer();
        Specification<Transaction> specification = searchTransactions(customer.getId(), request);

        PageRequest pageRequest = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize(),
                Sort.by(Sort.Direction.DESC, "createDate")
        );

        Page<Transaction> page = transactionRepository.findAll(specification, pageRequest);
        List<TransactionResponse> items = page.getContent().stream()
                .map(TransactionResponse::new)
                .toList();

        return GenericResponse.success(GenericPaginationResponse.from(page, items));
    }

    @Override
    public GenericResponse<TransactionResponse> getTransactionDetail(long id) {
        Customer customer = SecurityUtils.getCurrentCustomer();
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Transaction not found", HttpStatus.NOT_FOUND));

        if (transaction.getAccount().getCustomer().getId() != customer.getId()) {
            throw new BusinessException("Transaction not found", HttpStatus.NOT_FOUND);
        }

        return GenericResponse.success(new TransactionResponse(transaction));
    }

    private Specification<Transaction> searchTransactions(long customerId, TransactionFilterRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> accountJoin = root.join("account");
            Join<Object, Object> customerJoin = accountJoin.join("customer");
            predicates.add(criteriaBuilder.equal(customerJoin.get("id"), customerId));

            filterByDate(request, root, criteriaBuilder, predicates);
            filterByType(request, root, predicates);
            filterByStatus(request, root, predicates);

            if (request.getAccountId() != null) {
                predicates.add(criteriaBuilder.equal(accountJoin.get("id"), request.getAccountId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void filterByDate(
            TransactionFilterRequest request,
            jakarta.persistence.criteria.Root<Transaction> root,
            jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates
    ) {
        if (request.getFromDate() != null) {
            LocalDateTime fromDateTime = request.getFromDate().atStartOfDay();
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), fromDateTime));
        }
        if (request.getToDate() != null) {
            LocalDateTime toDateTime = request.getToDate().atTime(LocalTime.MAX);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), toDateTime));
        }
    }

    private void filterByType(
            TransactionFilterRequest request,
            jakarta.persistence.criteria.Root<Transaction> root,
            List<Predicate> predicates
    ) {
        TransactionType transactionType = request.getTransactionType();
        if (transactionType != null) {
            predicates.add(root.get("type").in(transactionType));
        }
    }

    private void filterByStatus(
            TransactionFilterRequest request,
            jakarta.persistence.criteria.Root<Transaction> root,
            List<Predicate> predicates
    ) {
        TransactionStatus status = request.getStatus();
        if (status != null) {
            predicates.add(root.get("status").in(status));
        }
    }
}

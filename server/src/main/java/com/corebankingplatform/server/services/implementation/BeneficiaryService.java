package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.beneficiary.BeneficiariesSearchRequest;
import com.corebankingplatform.server.dto.request.beneficiary.CreateBeneficiaryRequest;
import com.corebankingplatform.server.dto.request.beneficiary.UpdateBeneficiaryRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.beneficiary.BeneficiariesResponse;
import com.corebankingplatform.server.dto.response.beneficiary.BeneficiaryResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import com.corebankingplatform.server.entites.Beneficiary;
import com.corebankingplatform.server.entites.Customer;
import com.corebankingplatform.server.enums.BeneficiaryStatus;
import com.corebankingplatform.server.exception.BusinessException;
import com.corebankingplatform.server.repositories.interfaces.BeneficiaryRepository;
import com.corebankingplatform.server.security.SecurityUtils;
import com.corebankingplatform.server.services.interfaces.IBeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficiaryService implements IBeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public GenericResponse<GenericPaginationResponse<BeneficiariesResponse>> getBeneficiaries(BeneficiariesSearchRequest request) {
        Page<Beneficiary> page = beneficiaryRepository.findAll(request.toPageable());
        List<BeneficiariesResponse> items = page.getContent().stream().map(BeneficiariesResponse::new).toList();
        return  GenericResponse.success(GenericPaginationResponse.from(page, items));
    }

    @Override
    public GenericResponse<BeneficiaryResponse> getBeneficiaryDetail(long id) {
        Beneficiary beneficiary = getOwnedBeneficiary(id);
        return GenericResponse.success(new BeneficiaryResponse(beneficiary));
    }

    @Override
    public GenericResponse<BeneficiaryResponse> createBeneficiary(CreateBeneficiaryRequest request) {
        Customer customer = SecurityUtils.getCurrentCustomer();
        validateCreateRequest(request);
        validateAccountNumber(request.getAccountNumber());

        if (beneficiaryRepository.existsByCustomerIdAndAccountNumberAndStatus(
                customer.getId(),
                request.getAccountNumber(),
                BeneficiaryStatus.ACTIVE
        )) {
            throw new BusinessException("Beneficiary already exists");
        }

        Beneficiary beneficiary = Beneficiary.create(customer, request);

        return  GenericResponse.success(new BeneficiaryResponse(beneficiaryRepository.save(beneficiary)));
    }



    @Override
    public GenericResponse<BeneficiaryResponse> updateBeneficiary(long id, UpdateBeneficiaryRequest request) {
        Beneficiary beneficiary = getOwnedBeneficiary(id);

        if (request.getBankName() != null && !request.getBankName().isBlank()) {
            beneficiary.setBankName(request.getBankName());
        }
        if (request.getAccountHolderName() != null && !request.getAccountHolderName().isBlank()) {
            beneficiary.setAccountHolderName(request.getAccountHolderName());
        }
        if (request.getNickname() != null && !request.getNickname().isBlank()) {
            beneficiary.setNickname(request.getNickname());
        }

        return GenericResponse.success(new BeneficiaryResponse(beneficiaryRepository.save(beneficiary)));
    }

    @Override
    public GenericResponse<Boolean> deleteBeneficiary(long id) {
        Beneficiary beneficiary = getOwnedBeneficiary(id);
        beneficiary.setStatus(BeneficiaryStatus.INACTIVE);
        beneficiaryRepository.save(beneficiary);

        return GenericResponse.success(true);
    }

    private Beneficiary getOwnedBeneficiary(long id) {
        Customer customer = SecurityUtils.getCurrentCustomer();
        return beneficiaryRepository.findByIdAndCustomerId(id, customer.getId())
                .filter(beneficiary -> beneficiary.getStatus() == BeneficiaryStatus.ACTIVE)
                .orElseThrow(() -> new BusinessException("Beneficiary not found", HttpStatus.NOT_FOUND));
    }

    private void validateCreateRequest(CreateBeneficiaryRequest request) {
        if (request.getBankName() == null || request.getBankName().isBlank()) {
            throw new BusinessException("Bank name is required");
        }
        if (request.getAccountNumber() == null || request.getAccountNumber().isBlank()) {
            throw new BusinessException("Account number is required");
        }
        if (request.getAccountHolderName() == null || request.getAccountHolderName().isBlank()) {
            throw new BusinessException("Account holder name is required");
        }
        if (request.getNickname() == null || request.getNickname().isBlank()) {
            throw new BusinessException("Nickname is required");
        }
    }

    private void validateAccountNumber(String accountNumber) {
        if (!accountNumber.matches("\\d{8,20}")) {
            throw new BusinessException("Invalid account number format");
        }
    }
}

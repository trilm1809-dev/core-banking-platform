package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.beneficiary.BeneficiariesSearchRequest;
import com.corebankingplatform.server.dto.request.beneficiary.CreateBeneficiaryRequest;
import com.corebankingplatform.server.dto.request.beneficiary.UpdateBeneficiaryRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.beneficiary.BeneficiariesResponse;
import com.corebankingplatform.server.dto.response.beneficiary.BeneficiaryResponse;
import com.corebankingplatform.server.dto.response.pagination.GenericPaginationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBeneficiaryService {

    GenericResponse<BeneficiaryResponse> createBeneficiary(CreateBeneficiaryRequest request);

    GenericResponse<GenericPaginationResponse<BeneficiariesResponse>> getBeneficiaries(BeneficiariesSearchRequest request);

    GenericResponse<BeneficiaryResponse> getBeneficiaryDetail(long id);

    GenericResponse<BeneficiaryResponse> updateBeneficiary(long id, UpdateBeneficiaryRequest request);

    GenericResponse<Boolean> deleteBeneficiary(long id);
}

package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.beneficiary.CreateBeneficiaryRequest;
import com.corebankingplatform.server.dto.request.beneficiary.UpdateBeneficiaryRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.beneficiary.BeneficiaryResponse;
import com.corebankingplatform.server.services.interfaces.IBeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final IBeneficiaryService beneficiaryService;

    @PostMapping
    public ResponseEntity<GenericResponse<BeneficiaryResponse>> createBeneficiary(
            @RequestBody CreateBeneficiaryRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(beneficiaryService.createBeneficiary(request)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<BeneficiaryResponse>>> getBeneficiaries() {
        return ResponseEntity.ok(
                GenericResponse.success(beneficiaryService.getBeneficiaries())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<BeneficiaryResponse>> getBeneficiaryDetail(
            @PathVariable long id
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(beneficiaryService.getBeneficiaryDetail(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<BeneficiaryResponse>> updateBeneficiary(
            @PathVariable long id,
            @RequestBody UpdateBeneficiaryRequest request
    ) {
        return ResponseEntity.ok(
                GenericResponse.success(beneficiaryService.updateBeneficiary(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteBeneficiary(@PathVariable long id) {
        beneficiaryService.deleteBeneficiary(id);
        return ResponseEntity.ok(GenericResponse.success(null));
    }
}

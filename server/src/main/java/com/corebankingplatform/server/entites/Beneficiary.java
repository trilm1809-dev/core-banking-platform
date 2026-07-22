    package com.corebankingplatform.server.entites;

    import com.corebankingplatform.server.dto.request.beneficiary.CreateBeneficiaryRequest;
    import com.corebankingplatform.server.enums.BeneficiaryStatus;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.EnumType;
    import jakarta.persistence.Enumerated;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "beneficiaries")
    public class Beneficiary extends BaseEntity {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @Column(nullable = false, length = 100)
        private String bankName;

        @Column(nullable = false, length = 20)
        private String accountNumber;

        @Column(nullable = false, length = 100)
        private String accountHolderName;

        @Column(nullable = false, length = 100)
        private String nickname;

        @Builder.Default
        @Enumerated(EnumType.STRING)
        private BeneficiaryStatus status = BeneficiaryStatus.ACTIVE;

        public static Beneficiary create(
                Customer customer,
                CreateBeneficiaryRequest request
        ) {
            return Beneficiary.builder()
                    .customer(customer)
                    .bankName(request.getBankName())
                    .accountNumber(request.getAccountNumber())
                    .accountHolderName(request.getAccountHolderName())
                    .nickname(request.getNickname())
                    .status(BeneficiaryStatus.ACTIVE)
                    .build();
        }
    }

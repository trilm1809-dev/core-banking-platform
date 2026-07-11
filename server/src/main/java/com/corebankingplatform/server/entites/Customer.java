package com.corebankingplatform.server.entites;

import com.corebankingplatform.server.enums.CustomerStatus;
import com.corebankingplatform.server.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String customerCode;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, unique = true, length = 20)
    private String citizenId;

    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 255)
    private String address;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CustomerStatus status = CustomerStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Builder.Default
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BankAccount> bankAccounts = new ArrayList<>();

    public void addBankAccount(BankAccount account) {

        bankAccounts.add(account);

        account.setCustomer(this);

    }

    public void removeBankAccount(BankAccount account) {

        bankAccounts.remove(account);

        account.setCustomer(null);

    }

}

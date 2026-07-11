package com.corebankingplatform.server.entites;

import com.corebankingplatform.server.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfers")
public class Transfer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id", nullable = false)
    private BankAccount fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id", nullable = false)
    private BankAccount toAccount;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @OneToMany(mappedBy = "transfer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {

        transactions.add(transaction);

        transaction.setTransfer(this);

    }

    public void removeTransaction(Transaction transaction) {

        transactions.remove(transaction);

        transaction.setTransfer(null);

    }

}

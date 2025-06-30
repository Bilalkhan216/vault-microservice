package com.example.Islamic.Wallet.Application.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vault_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaultTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vaultId;

    private BigDecimal amount;

    private String direction; // "IN" or "OUT"

    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}

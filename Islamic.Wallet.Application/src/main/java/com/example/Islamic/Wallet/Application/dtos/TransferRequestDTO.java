package com.example.Islamic.Wallet.Application.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    private Long userId;
    private Long vaultId;
    private BigDecimal amount;
    private String direction; // IN or OUT
}

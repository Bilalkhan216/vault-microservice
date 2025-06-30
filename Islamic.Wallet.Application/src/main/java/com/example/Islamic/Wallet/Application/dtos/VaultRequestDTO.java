package com.example.Islamic.Wallet.Application.dtos;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class VaultRequestDTO {
 private Long userId;
 private String name;
 private BigDecimal goalAmount;
}

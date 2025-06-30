package com.example.Islamic.Wallet.Application.dtos;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class VaultResponseDTO {
 private Long id;
 private String name;
 private BigDecimal goalAmount;
 private BigDecimal balance;
}

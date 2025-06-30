package com.example.Islamic.Wallet.Application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Islamic.Wallet.Application.dtos.VaultResponseDTO;
import com.example.Islamic.Wallet.Application.entities.Vault;

import java.util.List;

public interface VaultRepository extends JpaRepository<Vault, Long> {
    List<Vault> findByUserId(Long userId);
    
    VaultResponseDTO getVaultById(Long vaultId);

}

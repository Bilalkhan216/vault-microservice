package com.example.Islamic.Wallet.Application.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Islamic.Wallet.Application.dtos.TransferRequestDTO;
import com.example.Islamic.Wallet.Application.dtos.VaultRequestDTO;
import com.example.Islamic.Wallet.Application.dtos.VaultResponseDTO;
import com.example.Islamic.Wallet.Application.entities.Vault;
import com.example.Islamic.Wallet.Application.entities.VaultTransaction;
import com.example.Islamic.Wallet.Application.repositories.VaultRepository;
import com.example.Islamic.Wallet.Application.repositories.VaultTransactionRepository;
import com.example.Islamic.Wallet.Application.services.VaultService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaultServiceImpl implements VaultService {

    private final VaultRepository vaultRepository;

    @Override
    public VaultResponseDTO createVault(VaultRequestDTO request) {
        Vault vault = Vault.builder()
                .userId(request.getUserId())
                .name(request.getName())
                .goalAmount(request.getGoalAmount())
                .build();
        vault = vaultRepository.save(vault);

        return mapToDTO(vault);
    }

    @Override
    public List<VaultResponseDTO> getVaultsByUserId(Long userId) {
        return vaultRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private VaultResponseDTO mapToDTO(Vault vault) {
        VaultResponseDTO dto = new VaultResponseDTO();
        dto.setId(vault.getId());
        dto.setName(vault.getName());
        dto.setGoalAmount(vault.getGoalAmount());
        dto.setBalance(vault.getBalance());
        return dto;
    }
    
    
    @Override
    public VaultResponseDTO getVaultById(Long vaultId) {
        Vault vault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found with id: " + vaultId));
        return mapToDTO(vault);
    }
    
    
    @Autowired
    private VaultTransactionRepository vaultTransactionRepository;

    @Override
    public void transferFunds(TransferRequestDTO request) {
        Vault vault = vaultRepository.findById(request.getVaultId())
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        BigDecimal amount = request.getAmount();

        if ("IN".equalsIgnoreCase(request.getDirection())) {
            // Add money to vault
            vault.setBalance(vault.getBalance().add(amount));
        } else if ("OUT".equalsIgnoreCase(request.getDirection())) {
            // Check for sufficient balance
            if (vault.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient vault balance");
            }
            // Subtract money from vault
            vault.setBalance(vault.getBalance().subtract(amount));
        } else {
            throw new RuntimeException("Invalid transfer direction. Use IN or OUT.");
        }

        // Save vault
        vaultRepository.save(vault);

        // Save transaction
        VaultTransaction txn = VaultTransaction.builder()
                .vaultId(vault.getId())
                .amount(amount)
                .direction(request.getDirection())
                .build();

        vaultTransactionRepository.save(txn);
    }


    
}

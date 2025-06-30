package com.example.Islamic.Wallet.Application.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Islamic.Wallet.Application.dtos.TransferRequestDTO;
import com.example.Islamic.Wallet.Application.dtos.VaultRequestDTO;
import com.example.Islamic.Wallet.Application.dtos.VaultResponseDTO;
import com.example.Islamic.Wallet.Application.services.VaultService;

import java.util.List;

@RestController
@RequestMapping("/vault")
@RequiredArgsConstructor
public class VaultController {

    private final VaultService vaultService;

    @PostMapping
    public VaultResponseDTO createVault(@RequestBody VaultRequestDTO request) {
        return vaultService.createVault(request);
    }

    @GetMapping("/user/{userId}")
    public List<VaultResponseDTO> getVaults(@PathVariable Long userId) {
        return vaultService.getVaultsByUserId(userId);
    }
    
    @GetMapping("/{vaultId}")
    public VaultResponseDTO getVaultById(@PathVariable Long vaultId) {
        return vaultService.getVaultById(vaultId);
    }
    
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequestDTO request) {
        vaultService.transferFunds(request);
        return ResponseEntity.ok("Transfer successful.");
    }


    
}

package com.example.Islamic.Wallet.Application.services;


import java.util.List;

import com.example.Islamic.Wallet.Application.dtos.TransferRequestDTO;
import com.example.Islamic.Wallet.Application.dtos.VaultRequestDTO;
import com.example.Islamic.Wallet.Application.dtos.VaultResponseDTO;

public interface VaultService {
    VaultResponseDTO createVault(VaultRequestDTO request);
    List<VaultResponseDTO> getVaultsByUserId(Long userId);
	VaultResponseDTO getVaultById(Long vaultId);
	
	void transferFunds(TransferRequestDTO request);
	

}

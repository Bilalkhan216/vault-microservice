package com.example.Islamic.Wallet.Application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Islamic.Wallet.Application.entities.VaultTransaction;

public interface VaultTransactionRepository extends JpaRepository<VaultTransaction, Long> {
}

package com.itau.transferAPI.persistence.repository;

import com.itau.transferAPI.persistence.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransferRepository
        extends JpaRepository<TransferEntity, UUID> {

    List<TransferEntity> findBySourceAccountOrDestinationAccountOrderByCreatedAtDesc(
            String sourceAccount,
            String destinationAccount
    );
}

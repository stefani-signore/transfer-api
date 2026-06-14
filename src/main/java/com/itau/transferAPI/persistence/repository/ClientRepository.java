package com.itau.transferAPI.persistence.repository;

import com.itau.transferAPI.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository
        extends JpaRepository<ClientEntity, UUID> {

    Optional<ClientEntity> findByAccountNumber(
            String accountNumber
    );

    boolean existsByAccountNumber(
            String accountNumber
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
       select c
       from ClientEntity c
       where c.accountNumber = :accountNumber
       """)
    Optional<ClientEntity> findByAccountNumberForUpdate(
            String accountNumber);

}

package com.itau.transferAPI.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transfer {

    private UUID id;
    private String sourceAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private TransferStatus status;
    private LocalDateTime createdAt;

    public Transfer() {
    }

    public Transfer(UUID id,
                    String sourceAccount,
                    String destinationAccount,
                    BigDecimal amount,
                    TransferStatus status,
                    LocalDateTime createdAt) {

        this.id = id;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

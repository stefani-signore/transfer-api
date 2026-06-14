package com.itau.transferAPI.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransferResponse(

        UUID id,
        String sourceAccount,
        String destinationAccount,
        BigDecimal amount,
        String status,
        LocalDateTime createdAt

) {
}

package com.itau.transferAPI.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ClientResponse(
        UUID id,
        String name,
        String accountNumber,
        BigDecimal balance
) {
}
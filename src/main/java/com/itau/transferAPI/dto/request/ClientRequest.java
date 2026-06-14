package com.itau.transferAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ClientRequest(

        @NotBlank
        String name,

        @NotBlank
        String accountNumber,

        @NotNull
        @PositiveOrZero
        BigDecimal balance

) {
}
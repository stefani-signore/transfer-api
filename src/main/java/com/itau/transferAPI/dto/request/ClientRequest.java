package com.itau.transferAPI.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ClientRequest(

        @NotBlank
        @Schema(
                example = "Stefani",
                description = "Nome do cliente")
        String name,

        @NotBlank
        @Schema(
                example = "11111",
                description = "Número único da conta")
        String accountNumber,

        @NotNull
        @PositiveOrZero
        @Schema(
                example = "5000.00",
                description = "Saldo inicial da conta")
        BigDecimal balance

) {
}
package com.itau.transferAPI.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(

        @NotBlank
        @Schema(
                example = "11111",
                description = "Número de origem da conta")
        String sourceAccount,

        @NotBlank
        @Schema(
                example = "22222",
                description = "Número da conta destino")
        String destinationAccount,

        @NotNull
        @DecimalMin("0.01")
        @Schema(
                example = "1000.00",
                description = "Quantia a ser transferida")
        BigDecimal amount

) {
}
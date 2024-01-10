package com.desafio.picpay.dtos;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

public record TransactionDto(@NotBlank Long payer, @NotBlank Long payee, @NotNull BigDecimal value) {

    }


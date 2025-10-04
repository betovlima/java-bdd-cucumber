package com.example.bdd.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductInput(
        @NotBlank(message = "nome é obrigatório") String nome,
        @NotNull(message = "preco é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "preco deve ser > 0")
        BigDecimal preco,
        String descricao
) { }
package com.futuro_digital.spring_graphql_postgres_api.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductInput (
        @NotBlank(message = "Name cant be null or blank")
        String name,
        String description,
        @NotNull(message = "Price cant be null")
        @Positive(message = "Price cant be 0 or negative")
        Double price,
        @NotNull(message = "Quantity cant be null")
        @Positive(message = "Quantity cant be 0 or negative")
        @Min(value = 1)
        Integer quantity
) {}

package com.example.jpa_auditing.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
    @NotBlank String name,
    @NotNull @Min(0) Double price,
    @NotBlank String description
) {
}

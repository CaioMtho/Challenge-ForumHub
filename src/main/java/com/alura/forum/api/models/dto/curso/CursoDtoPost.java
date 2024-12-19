package com.alura.forum.api.models.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CursoDtoPost(
        @NotNull UUID id,
        @NotBlank String nome,
        @NotBlank String categoria
        ) {
}

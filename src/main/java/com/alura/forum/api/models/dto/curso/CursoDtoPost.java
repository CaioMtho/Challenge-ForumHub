package com.alura.forum.api.models.dto.curso;

import jakarta.validation.constraints.NotBlank;
public record CursoDtoPost(
        @NotBlank String nome,
        @NotBlank String categoria
        ) {
}

package com.alura.forum.api.models.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PerfilDtoPost(
        @NotBlank String nome,
        @NotNull UUID usuarioId
        ) {
}

package com.alura.forum.api.models.dto.perfil;

import com.alura.forum.api.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerfilDtoPost(
        @NotBlank String nome,
        @NotNull Usuario usuario
        ) {
}

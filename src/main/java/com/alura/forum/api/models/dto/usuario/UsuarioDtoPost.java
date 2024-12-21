package com.alura.forum.api.models.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record UsuarioDtoPost(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}

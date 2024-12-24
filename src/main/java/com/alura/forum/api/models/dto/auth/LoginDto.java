package com.alura.forum.api.models.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank String login,
        @NotBlank String senha
) {
}

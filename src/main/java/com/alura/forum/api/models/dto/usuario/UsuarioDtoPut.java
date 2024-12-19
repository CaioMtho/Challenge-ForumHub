package com.alura.forum.api.models.dto.usuario;

public record UsuarioDtoPut(
        String nome,
        String email,
        String senha
) {
}

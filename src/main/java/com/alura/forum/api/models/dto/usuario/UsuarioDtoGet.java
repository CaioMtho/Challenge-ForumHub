package com.alura.forum.api.models.dto.usuario;

import com.alura.forum.api.models.Usuario;

import java.util.UUID;

public record UsuarioDtoGet(
        UUID id,
        String nome,
        String email,
        String senha
) {
    public UsuarioDtoGet(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}

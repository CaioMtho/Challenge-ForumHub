package com.alura.forum.api.models.dto.usuario;

import com.alura.forum.api.models.domain.Perfil;
import com.alura.forum.api.models.domain.Usuario;

import java.util.List;
import java.util.UUID;

public record UsuarioDtoGet(
        UUID id,
        String nome,
        String email,
        String senha,
        List<Perfil> perfis
) {
    public UsuarioDtoGet(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
    }
}

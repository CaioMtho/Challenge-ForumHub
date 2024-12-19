package com.alura.forum.api.models.dto.perfil;

import com.alura.forum.api.models.Perfil;
import com.alura.forum.api.models.Usuario;

import java.util.UUID;

public record PerfilDtoGet(
        UUID id,
        String nome,
        Usuario usuario
) {
    public PerfilDtoGet(Perfil perfil){
        this(perfil.getId(), perfil.getNome(), perfil.getUsuario());
    }
}

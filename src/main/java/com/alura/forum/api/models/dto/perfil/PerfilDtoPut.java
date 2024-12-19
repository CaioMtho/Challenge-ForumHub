package com.alura.forum.api.models.dto.perfil;

import com.alura.forum.api.models.Usuario;

public record PerfilDtoPut(
        String nome,
        Usuario usuario
) {
}

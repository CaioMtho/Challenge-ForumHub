package com.alura.forum.api.models.dto.curso;

import com.alura.forum.api.models.Curso;

import java.util.UUID;

public record CursoDtoGet(
        UUID id,
        String nome,
        String categoria
) {
    public CursoDtoGet(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}

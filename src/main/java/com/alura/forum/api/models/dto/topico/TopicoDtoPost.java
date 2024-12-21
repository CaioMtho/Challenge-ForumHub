package com.alura.forum.api.models.dto.topico;

import com.alura.forum.api.models.domain.Curso;
import com.alura.forum.api.models.domain.Perfil;
import com.alura.forum.api.models.domain.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TopicoDtoPost(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull UUID autorId,
        @NotNull UUID cursoId
) {
    public TopicoDtoPost (Topico topico)
    {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}

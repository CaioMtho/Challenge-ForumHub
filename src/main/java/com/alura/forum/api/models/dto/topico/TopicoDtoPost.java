package com.alura.forum.api.models.dto.topico;

import com.alura.forum.api.models.Curso;
import com.alura.forum.api.models.Perfil;
import com.alura.forum.api.models.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDtoPost(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull Perfil autor,
        @NotNull Curso curso
) {
    public TopicoDtoPost (Topico topico)
    {
        this(topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}

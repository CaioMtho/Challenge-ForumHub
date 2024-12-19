package com.alura.forum.api.models.dto.topico;

import com.alura.forum.api.models.Curso;
import com.alura.forum.api.models.Perfil;
import com.alura.forum.api.models.Status;
import com.alura.forum.api.models.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDtoGet(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull LocalDateTime dataCriacao,
        @NotNull Status status,
        @NotNull Perfil autor,
        @NotNull Curso curso
        ) {

    public TopicoDtoGet(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}

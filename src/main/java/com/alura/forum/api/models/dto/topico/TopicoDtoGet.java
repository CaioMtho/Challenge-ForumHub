package com.alura.forum.api.models.dto.topico;

import com.alura.forum.api.models.domain.Curso;
import com.alura.forum.api.models.domain.Perfil;
import com.alura.forum.api.models.domain.Status;
import com.alura.forum.api.models.domain.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TopicoDtoGet(
        @NotNull UUID id,
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull LocalDateTime dataCriacao,
        @NotNull Status status,
        @NotNull Perfil autor,
        @NotNull Curso curso
        ) {

    public TopicoDtoGet(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}

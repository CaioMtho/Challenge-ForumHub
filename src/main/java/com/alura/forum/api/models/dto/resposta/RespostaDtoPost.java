package com.alura.forum.api.models.dto.resposta;

import com.alura.forum.api.models.domain.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RespostaDtoPost(
        @NotBlank String mensagem,
        @NotNull UUID topicoId
) {
}

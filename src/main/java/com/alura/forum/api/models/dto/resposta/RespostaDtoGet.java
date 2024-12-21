package com.alura.forum.api.models.dto.resposta;

import com.alura.forum.api.models.domain.Resposta;

import java.util.UUID;

public record RespostaDtoGet(
        UUID id,
        String mensagem,
        UUID topicoId,
        boolean solucao
) {
    public RespostaDtoGet(Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getTopico().getId(), resposta.isSolucao());
    }
}

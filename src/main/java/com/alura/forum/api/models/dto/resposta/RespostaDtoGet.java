package com.alura.forum.api.models.dto.resposta;

import com.alura.forum.api.models.Resposta;
import com.alura.forum.api.models.Topico;

public record RespostaDtoGet(
        String mensagem,
        Topico topico,
        boolean solucao
) {
    public RespostaDtoGet(Resposta resposta){
        this(resposta.getMensagem(), resposta.getTopico(), resposta.isSolucao());
    }
}

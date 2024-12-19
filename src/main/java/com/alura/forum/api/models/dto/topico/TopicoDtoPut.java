package com.alura.forum.api.models.dto.topico;

import com.alura.forum.api.models.Status;
import com.alura.forum.api.models.Topico;

public record TopicoDtoPut(
        String titulo,
        String mensagem,
        Status status
) {
    public TopicoDtoPut(Topico topico){
        this(topico.getTitulo(), topico.getMensagem(), topico.getStatus());
    }
}
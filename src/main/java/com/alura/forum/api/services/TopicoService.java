package com.alura.forum.api.services;
import com.alura.forum.api.models.Topico;
import com.alura.forum.api.models.dto.topico.TopicoDtoGet;
import com.alura.forum.api.models.dto.topico.TopicoDtoPost;
import com.alura.forum.api.models.dto.topico.TopicoDtoPut;
import com.alura.forum.api.repositories.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.alura.forum.api.exception.exceptions.ResourceNotFoundException;

import java.util.UUID;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico create(TopicoDtoPost topicoDtoPost) {
        return topicoRepository.save(new Topico(topicoDtoPost));
    }

    public Page<TopicoDtoGet> getAll(Pageable pageable) {
        return topicoRepository.findAll(pageable).map(TopicoDtoGet::new);
    }

    public TopicoDtoGet getById(UUID id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado para o ID: " + id));
        return new TopicoDtoGet(topico);
    }

    @Transactional
    public TopicoDtoGet update(UUID id, TopicoDtoPut topicoDtoPut) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado para o ID: " + id));
        if (topicoDtoPut.titulo() != null) {
            topico.setTitulo(topicoDtoPut.titulo());
        }
        if (topicoDtoPut.mensagem() != null) {
            topico.setMensagem(topicoDtoPut.mensagem());
        }
        if (topicoDtoPut.status() != null) {
            topico.setStatus(topicoDtoPut.status());
        }
        return new TopicoDtoGet(topicoRepository.save(topico));
    }

    @Transactional
    public void delete(UUID id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico não encontrado para o ID: " + id));
        topicoRepository.delete(topico);
    }
}

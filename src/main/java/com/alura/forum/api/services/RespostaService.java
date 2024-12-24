package com.alura.forum.api.services;

import com.alura.forum.api.config.exception.exceptions.ResourceNotFoundException;
import com.alura.forum.api.models.domain.Resposta;
import com.alura.forum.api.models.dto.resposta.RespostaDtoGet;
import com.alura.forum.api.models.dto.resposta.RespostaDtoPost;
import com.alura.forum.api.models.dto.resposta.RespostaDtoPut;
import com.alura.forum.api.repositories.RespostaRepository;
import com.alura.forum.api.repositories.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RespostaService {
    @Autowired
    private RespostaRepository respostaRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    public List<RespostaDtoGet> getRespostas(UUID topicoId) {
        var topico = topicoRepository.findById(topicoId)
                .orElseThrow(()-> new ResourceNotFoundException("Topico não encontrado"));
        return respostaRepository.findByTopico(topico)
                .stream()
                .map(RespostaDtoGet::new)
                .collect(Collectors.toList());
    }

    public RespostaDtoGet getResposta(UUID id) {
        return new RespostaDtoGet(respostaRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Resposta não encontrada")));
    }

    public RespostaDtoGet createResposta(RespostaDtoPost respostaDtoPost) {
        Resposta resposta = respostaRepository.save(convertDtoToResposta(respostaDtoPost));
        return new RespostaDtoGet(resposta);
    }

    public RespostaDtoGet updateResposta(UUID id, RespostaDtoPut respostaDtoPut) {
        var resposta = respostaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Resposta não encontrada"));
        if(respostaDtoPut.mensagem() != null) {
            resposta.setMensagem(respostaDtoPut.mensagem());
        }
        if (respostaDtoPut.solucao() != resposta.isSolucao()){
            resposta.setSolucao(respostaDtoPut.solucao());
        }
        return new RespostaDtoGet(respostaRepository.save(resposta));
    }

    public void deleteResposta(UUID id) {
        var resposta = respostaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Resposta não encontrada"));
        respostaRepository.delete(resposta);
    }

    public Resposta convertDtoToResposta(RespostaDtoPost respostaDtoPost) {
        var topico = topicoRepository.findById(respostaDtoPost.topicoId())
                .orElseThrow(()-> new ResourceNotFoundException("Topico não encontrado"));
        return new Resposta(respostaDtoPost.mensagem(), topico);
    }
}

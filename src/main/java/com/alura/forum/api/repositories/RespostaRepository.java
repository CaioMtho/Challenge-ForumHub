package com.alura.forum.api.repositories;

import com.alura.forum.api.models.domain.Resposta;
import com.alura.forum.api.models.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RespostaRepository extends JpaRepository<Resposta, UUID> {
    List<Resposta> findByTopico(Topico topico);
}

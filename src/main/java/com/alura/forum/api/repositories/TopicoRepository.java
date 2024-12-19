package com.alura.forum.api.repositories;

import com.alura.forum.api.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TopicoRepository extends JpaRepository<Topico, UUID> {
    Optional<Topico> findById(UUID topicoId);
    Page<Topico> findAll(Pageable pageable);
}

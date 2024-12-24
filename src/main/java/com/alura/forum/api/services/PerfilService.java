package com.alura.forum.api.services;

import com.alura.forum.api.config.exception.exceptions.ResourceNotFoundException;
import com.alura.forum.api.models.domain.Perfil;
import com.alura.forum.api.models.domain.Usuario;
import com.alura.forum.api.models.dto.perfil.PerfilDtoGet;
import com.alura.forum.api.models.dto.perfil.PerfilDtoPost;
import com.alura.forum.api.models.dto.perfil.PerfilDtoPut;
import com.alura.forum.api.repositories.PerfilRepository;
import com.alura.forum.api.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PerfilDtoGet> getPerfisByUsuario(UUID usuarioId) {
        Usuario usuario = usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return Optional.ofNullable(perfilRepository.findByUsuario(usuario))
                .orElse(Collections.emptyList())
                .stream()
                .map(PerfilDtoGet::new)
                .collect(Collectors.toList());
    }

    public PerfilDtoGet getPerfil(UUID id) {
        return perfilRepository.findById(id)
                .map(PerfilDtoGet::new)
                .orElseThrow(()-> new ResourceNotFoundException("perfil não encontrado"));
    }

    @Transactional
    public PerfilDtoGet createPerfil(PerfilDtoPost perfilDtoPost) {
        return new PerfilDtoGet(perfilRepository.save(converterDtoToPerfil(perfilDtoPost)));
    }

    @Transactional
    public PerfilDtoGet updatePerfil(UUID id, PerfilDtoPut perfilDtoPut) {
        Perfil perfil = perfilRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado"));
        if(perfilDtoPut.nome() != null) {
            perfil.setNome(perfilDtoPut.nome());
        }
        if(perfilDtoPut.usuario() != null) {
            perfil.setUsuario(perfilDtoPut.usuario());
        }
        return new PerfilDtoGet(perfilRepository.save(perfil));
    }

    @Transactional
    public void deletePerfil(UUID id) {
        Perfil perfil = perfilRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Perfil não encontrado"));
        perfilRepository.delete(perfil);
    }

    private Perfil converterDtoToPerfil(PerfilDtoPost perfilDtoPost) {
        Usuario usuario = usuarioRepository.findById(perfilDtoPost.usuarioId())
                .orElseThrow(()->new ResourceNotFoundException("Usuario não encontrado"));
        return new Perfil(perfilDtoPost.nome(), usuario);
    }
}

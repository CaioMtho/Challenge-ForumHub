package com.alura.forum.api.services;

import com.alura.forum.api.exception.exceptions.ResourceNotFoundException;
import com.alura.forum.api.models.domain.Curso;
import com.alura.forum.api.models.dto.curso.CursoDtoGet;
import com.alura.forum.api.models.dto.curso.CursoDtoPost;
import com.alura.forum.api.models.dto.curso.CursoDtoPut;
import com.alura.forum.api.repositories.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Page<CursoDtoGet> getAll(Pageable pageable) {
        return cursoRepository.findAll(pageable).map(CursoDtoGet::new);
    }
    public CursoDtoGet getById(UUID id) {
        return new CursoDtoGet(cursoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Curso não encontrado")));
    }

    @Transactional
    public CursoDtoGet create(CursoDtoPost cursoDtoPost) {
        var curso = new Curso(cursoDtoPost);
        return new CursoDtoGet(cursoRepository.save(curso));
    }

    @Transactional
    public CursoDtoGet update(UUID id, CursoDtoPut cursoDtoPut) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Curso não encontrado"));
        if(cursoDtoPut.nome() != null) {
            curso.setNome(cursoDtoPut.nome());
        }
        if(cursoDtoPut.categoria() != null) {
            curso.setCategoria(cursoDtoPut.categoria());
        }
        return new CursoDtoGet(cursoRepository.save(curso));
    }

    @Transactional
    public void delete(UUID id) {
        var curso = cursoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Curso não encontrado"));
        cursoRepository.delete(curso);
    }


}

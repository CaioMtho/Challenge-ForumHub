package com.alura.forum.api.controllers;

import com.alura.forum.api.models.dto.curso.CursoDtoGet;
import com.alura.forum.api.models.dto.curso.CursoDtoPost;
import com.alura.forum.api.models.dto.curso.CursoDtoPut;
import com.alura.forum.api.services.CursoService;
import com.alura.forum.api.services.UUIDService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<Page<CursoDtoGet>> getCursos(Pageable pageable) {
        return ResponseEntity.ok(cursoService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDtoGet> getCurso(@PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        return ResponseEntity.ok(cursoService.getById(uuid));
    }

    @PostMapping
    public ResponseEntity<CursoDtoGet> postCurso(@RequestBody @Valid CursoDtoPost cursoDtoPost) {
        var curso = cursoService.create(cursoDtoPost);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(curso.id())
                .toUri();
        return ResponseEntity.created(uri).body(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDtoGet> putCurso(@PathVariable String id,
                                                @RequestBody CursoDtoPut cursoDtoPut) {
        UUID uuid = UUIDService.valideUUID(id);
        var curso = cursoService.update(uuid, cursoDtoPut);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        cursoService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

}

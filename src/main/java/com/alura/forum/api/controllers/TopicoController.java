package com.alura.forum.api.controllers;
import com.alura.forum.api.models.domain.Topico;
import com.alura.forum.api.models.dto.topico.TopicoDtoGet;
import com.alura.forum.api.models.dto.topico.TopicoDtoPost;
import com.alura.forum.api.models.dto.topico.TopicoDtoPut;
import com.alura.forum.api.services.TopicoService;
import com.alura.forum.api.services.UUIDService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<TopicoDtoGet>> getTopicos(
            @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(topicoService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDtoGet> getTopico(@PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        TopicoDtoGet topico = topicoService.getById(uuid);
        return ResponseEntity.ok(topico);
    }

    @PostMapping
    public ResponseEntity<Topico> postTopico(@RequestBody @Valid TopicoDtoPost topicoDtoPost) {
        Topico criado = topicoService.create(topicoDtoPost);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criado.getId())
                .toUri();
        return ResponseEntity.created(uri).body(criado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        topicoService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDtoGet> updateTopico(
            @PathVariable String id,
            @RequestBody @Valid TopicoDtoPut topicoDtoPut) {
        UUID uuid = UUIDService.valideUUID(id);
        TopicoDtoGet topico = topicoService.update(uuid, topicoDtoPut);
        return ResponseEntity.ok(topico);
    }
}


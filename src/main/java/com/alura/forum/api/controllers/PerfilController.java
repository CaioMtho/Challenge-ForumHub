package com.alura.forum.api.controllers;
import com.alura.forum.api.models.domain.Perfil;
import com.alura.forum.api.models.dto.perfil.PerfilDtoGet;
import com.alura.forum.api.models.dto.perfil.PerfilDtoPost;
import com.alura.forum.api.models.dto.perfil.PerfilDtoPut;
import com.alura.forum.api.models.dto.topico.TopicoDtoGet;
import com.alura.forum.api.services.PerfilService;
import com.alura.forum.api.services.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PerfilDtoGet>> getPerfis(@PathVariable String usuarioId) {
        UUID uuid = UUIDService.valideUUID(usuarioId);
        return ResponseEntity.ok(perfilService.getPerfisByUsuario(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDtoGet> getPerfilById(@PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        return ResponseEntity.ok(perfilService.getPerfil(uuid));
    }

    @PostMapping
    public ResponseEntity<PerfilDtoGet> createPerfil(@RequestBody PerfilDtoPost perfilDtoPost) {
        PerfilDtoGet perfil = perfilService.createPerfil(perfilDtoPost);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(perfil.id())
                .toUri();
        return ResponseEntity.created(uri).body(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilDtoGet> updatePerfil(
            @PathVariable String id,
            @RequestBody PerfilDtoPut perfilDtoPut) {
        var uuid = UUIDService.valideUUID(id);
        PerfilDtoGet perfil = perfilService.updatePerfil(uuid, perfilDtoPut);
        return ResponseEntity.ok(perfil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable String id){
        perfilService.deletePerfil(UUIDService.valideUUID(id));
        return ResponseEntity.noContent().build();
    }
}

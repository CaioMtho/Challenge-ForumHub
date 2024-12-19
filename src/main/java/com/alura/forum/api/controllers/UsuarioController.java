package com.alura.forum.api.controllers;

import com.alura.forum.api.models.Usuario;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoGet;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoPost;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoPut;
import com.alura.forum.api.services.UUIDService;
import com.alura.forum.api.services.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDtoGet>> getUsuarios(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getUsuarios(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDtoGet> getUsuario(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.ok(usuarioService.getUsuario(uuid));
    }

    @PostMapping
    public ResponseEntity<UsuarioDtoGet> postUsuario(@RequestBody @Valid UsuarioDtoPost usuarioDtoPost) {
        Usuario usuario = usuarioService.createUsuario(usuarioDtoPost);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new UsuarioDtoGet(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoGet> updateUsuario(
            @PathVariable String id,
            @RequestBody UsuarioDtoPut usuarioDtoPut){
        UUID uuid = UUIDService.valideUUID(id);
        UsuarioDtoGet usuario = usuarioService.updateUsuario(uuid, usuarioDtoPut);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity
                .ok()
                .header("Location", location.toString() )
                .body(usuario);
    }
}

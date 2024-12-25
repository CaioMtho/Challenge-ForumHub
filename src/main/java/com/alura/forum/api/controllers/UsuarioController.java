package com.alura.forum.api.controllers;

import com.alura.forum.api.models.domain.Usuario;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoGet;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoPost;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoPut;
import com.alura.forum.api.services.UUIDService;
import com.alura.forum.api.services.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @SecurityRequirement(name = "bearer-key")
    @GetMapping
    public ResponseEntity<Page<UsuarioDtoGet>> getUsuarios(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getUsuarios(pageable));
    }

    @SecurityRequirement(name = "bearer-key")
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

    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDtoGet> updateUsuario(
            @PathVariable String id,
            @RequestBody UsuarioDtoPut usuarioDtoPut){
        UUID uuid = UUIDService.valideUUID(id);
        UsuarioDtoGet usuario = usuarioService.updateUsuario(uuid, usuarioDtoPut);
        return ResponseEntity.ok(usuario);
    }
}

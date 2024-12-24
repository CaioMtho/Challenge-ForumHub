package com.alura.forum.api.services;

import com.alura.forum.api.config.exception.exceptions.ResourceNotFoundException;
import com.alura.forum.api.models.domain.Usuario;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoGet;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoPost;
import com.alura.forum.api.models.dto.usuario.UsuarioDtoPut;
import com.alura.forum.api.repositories.TopicoRepository;
import com.alura.forum.api.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class  UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsuarioDtoGet> getUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(UsuarioDtoGet::new);
    }

    public UsuarioDtoGet getUsuario(UUID id) {
       Usuario usuario = usuarioRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
       return new UsuarioDtoGet(usuario);
    }

    @Transactional
    public Usuario createUsuario(UsuarioDtoPost usuarioDtoPost) {
        return usuarioRepository.save(new Usuario(usuarioDtoPost.nome(), usuarioDtoPost.email(), passwordEncoder.encode(usuarioDtoPost.senha()) ));
    }
    @Transactional
    public void deleteUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public UsuarioDtoGet updateUsuario(UUID id, UsuarioDtoPut usuarioDtoPut) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado"));
        if(usuarioDtoPut.nome() != null) {
            usuario.setNome(usuarioDtoPut.nome());
        }
        if(usuarioDtoPut.email() != null) {
            usuario.setEmail(usuarioDtoPut.email());
        }
        return new UsuarioDtoGet(usuarioRepository.save(usuario));
    }
}

package com.alura.forum.api.repositories;

import com.alura.forum.api.models.domain.Perfil;
import com.alura.forum.api.models.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
 List<Perfil> findByUsuario(Usuario usuario);
}

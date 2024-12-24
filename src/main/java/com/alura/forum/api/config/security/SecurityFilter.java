package com.alura.forum.api.config.security;

import com.alura.forum.api.config.exception.exceptions.ResourceNotFoundException;
import com.alura.forum.api.models.ErrorResponse;
import com.alura.forum.api.repositories.UsuarioRepository;
import com.alura.forum.api.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var token = recuperarToken(request);
            if (token != null) {
                var subject = tokenService.getSubject(token);
                var usuario = usuarioRepository.findByEmail(subject)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (ResourceNotFoundException ex) {
            sendErrorResponse(response, HttpStatus.NOT_FOUND, "Usuário não encontrado", ex.getMessage(), request.getRequestURI());
        } catch (Exception ex) {
            sendErrorResponse(response, HttpStatus.FORBIDDEN, "Token inválido ou ausente", ex.getMessage(), request.getRequestURI());
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String error, String message, String path) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                error,
                message,
                path
        );
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(errorResponse));
    }
}



package com.alura.forum.api.controllers;

import com.alura.forum.api.models.domain.Usuario;
import com.alura.forum.api.models.dto.auth.LoginDto;
import com.alura.forum.api.models.dto.auth.TokenDto;
import com.alura.forum.api.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.getToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }
}

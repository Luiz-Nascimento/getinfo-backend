package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.LoginRequest;
import com.getinfo.contratos.DTOs.UsuarioRegistroDTO;
import com.getinfo.contratos.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody UsuarioRegistroDTO dto) {
        usuarioService.registrarNovoUsuario(dto);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

            Authentication authentication = authenticationManager.authenticate(authToken);


            return ResponseEntity.ok("Login realizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }
    }


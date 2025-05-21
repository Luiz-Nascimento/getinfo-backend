package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.UsuarioRegistroDTO;
import com.getinfo.contratos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody UsuarioRegistroDTO dto) {
        usuarioService.registrarNovoUsuario(dto);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

}

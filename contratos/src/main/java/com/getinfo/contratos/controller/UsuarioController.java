package com.getinfo.contratos.controller;


import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioExibirDTO> listarUsuarios() {
        return usuarioService.usuarioExibirDTOS();
    }

}

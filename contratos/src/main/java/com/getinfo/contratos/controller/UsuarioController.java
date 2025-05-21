package com.getinfo.contratos.controller;


import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioExibirDTO> listarUsuarios() {
        return usuarioService.usuarioExibirDTOS();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibirDTO> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioExibirDTO> usuarioExibirDTO = usuarioService.buscarPorIdDTO(id);
        if (usuarioExibirDTO.isPresent()) {

            return ResponseEntity.ok(usuarioExibirDTO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {

        if(usuarioService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

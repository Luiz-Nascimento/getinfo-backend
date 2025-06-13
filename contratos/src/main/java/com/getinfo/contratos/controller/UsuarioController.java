package com.getinfo.contratos.controller;


import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "Endpoints para usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Exibe informações sobre todos usuários cadastrados no sistema")
    @GetMapping
    public List<UsuarioExibirDTO> listarUsuarios() {
        return usuarioService.usuarioExibirDTOS();
    }

    @Operation(summary = "Exibe informações de um usuário cadastrado")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibirDTO> buscarPorId(@PathVariable Long id) {
        Optional<UsuarioExibirDTO> usuarioExibirDTO = usuarioService.buscarPorIdDTO(id);
        if (usuarioExibirDTO.isPresent()) {

            return ResponseEntity.ok(usuarioExibirDTO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove um usuário cadastrado do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {

        if(usuarioService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

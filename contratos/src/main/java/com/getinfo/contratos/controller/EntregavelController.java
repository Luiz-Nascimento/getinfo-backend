package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.service.EntregavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entregaveis")
public class EntregavelController {

    @Autowired
    private EntregavelService entregavelService;

    @PostMapping
    public ResponseEntity<EntregavelExibirDTO> criar(@RequestBody EntregavelCreateDTO dto) {
        return ResponseEntity.ok(entregavelService.criarEntregavel(dto));
    }

    @GetMapping
    public ResponseEntity<List<EntregavelExibirDTO>> listarTodos() {
        return ResponseEntity.ok(entregavelService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entregavelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

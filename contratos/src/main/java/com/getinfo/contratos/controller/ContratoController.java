package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.ContratoUpdateDTO;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.mappers.ContratoMapper;
import com.getinfo.contratos.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoService contratoService;
    private final ContratoMapper contratoMapper;

    public ContratoController(ContratoService contratoService, ContratoMapper contratoMapper) {
        this.contratoService = contratoService;
        this.contratoMapper = contratoMapper;
    }

    @GetMapping
    public ResponseEntity<List<ContratoExibirDTO>> listarTodos() {
        List<Contrato> contratos = contratoService.listarTodas();
        return ResponseEntity.ok(contratoMapper.entityListToExibirDTOList(contratos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoExibirDTO> buscarPorId(@PathVariable Long id) {
        return contratoService.buscarPorId(id)
                .map(contratoMapper::entityToExibirDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContratoExibirDTO> criar(@RequestBody ContratoCreateDTO dto) {
        Contrato contrato = contratoService.salvarComDTO(dto);
        URI location = URI.create("/contratos/" + contrato.getId());
        return ResponseEntity.created(location).body(contratoMapper.entityToExibirDTO(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoExibirDTO> atualizar(@PathVariable Long id, @RequestBody ContratoUpdateDTO dto) {
        Contrato contratoAtualizado = contratoService.atualizar(id, dto);
        return ResponseEntity.ok(contratoMapper.entityToExibirDTO(contratoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contratoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

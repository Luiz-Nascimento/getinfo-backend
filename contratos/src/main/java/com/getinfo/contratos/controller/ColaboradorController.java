package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ColaboradorCreateDTO;
import com.getinfo.contratos.DTOs.ColaboradorExibirDTO;
import com.getinfo.contratos.entity.Colaborador;
import com.getinfo.contratos.mappers.ColaboradorMapper;
import com.getinfo.contratos.repository.ColaboradorRepository;
import com.getinfo.contratos.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @GetMapping
    public List<ColaboradorExibirDTO> listarTodas() {
        return colaboradorService.listarDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorExibirDTO> buscarPorId(@PathVariable Long id) {
        Optional<ColaboradorExibirDTO> colaboradorExibirDTO = colaboradorService.buscarPorIdDTO(id);
        return colaboradorExibirDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColaboradorExibirDTO> salvar(@RequestBody ColaboradorCreateDTO colaboradorCreateDTO) {
        Colaborador colaborador = colaboradorService.CreateDTOtoEntity(colaboradorCreateDTO);
        colaboradorRepository.save(colaborador);
        ColaboradorExibirDTO response = colaboradorService.entityToExibirDTO(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        Optional<Colaborador> colaborador = colaboradorService.buscarPorId(id);
        if (colaborador.isPresent()) {
            colaboradorService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

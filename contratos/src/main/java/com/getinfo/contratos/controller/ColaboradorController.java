package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ColaboradorCreateDTO;
import com.getinfo.contratos.DTOs.ColaboradorExibirDTO;
import com.getinfo.contratos.DTOs.ColaboradorPatchDTO;
import com.getinfo.contratos.entity.Colaborador;
import com.getinfo.contratos.mappers.ColaboradorMapper;
import com.getinfo.contratos.repository.ColaboradorRepository;
import com.getinfo.contratos.service.ColaboradorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colaboradores")
@Tag(name = "Colaborador", description = "Endpoints para colaborador")
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
    public ResponseEntity<ColaboradorExibirDTO> salvar(@RequestBody @Valid ColaboradorCreateDTO colaboradorCreateDTO) {
        Colaborador colaborador = colaboradorService.CreateDTOtoEntity(colaboradorCreateDTO);
        colaboradorService.salvar(colaborador);
        ColaboradorExibirDTO response = colaboradorService.entityToExibirDTO(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ColaboradorExibirDTO> atualizarParcial(@PathVariable Long id, @RequestBody @Valid ColaboradorPatchDTO colaboradorAtualizar) {
        ColaboradorExibirDTO colaboradorAtualizado = colaboradorService.atualizarParcial(id, colaboradorAtualizar);
        if (colaboradorAtualizado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(colaboradorAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/desligar/{id}")
    public ResponseEntity<Void> desligar(@PathVariable Long id) {
        colaboradorService.desativar(id);
        return ResponseEntity.noContent().build();
    }

}

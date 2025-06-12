package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaPatchDTO;
import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.mappers.EmpresaMapper;
import com.getinfo.contratos.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresa", description = "Endpoints para empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaMapper empresaMapper;


    @Operation(summary = "Lista todas empresas cadastradas",
            description = "Retorna uma lista com todas empresas cadastradas no sistema.")
    @GetMapping
    public List<EmpresaExibirDTO> listarTodas() {
        return empresaService.listAllPublic();
    }

    @Operation(summary = "Lista empresa por id",
            description = "Retorna uma empresa cadastrada pelo id especificado, e com os campos do DTO de exibição.")
    @GetMapping("/id/{id}")
    public ResponseEntity<EmpresaExibirDTO> buscarPorId(@PathVariable Long id) {
        EmpresaExibirDTO empresa = empresaService.buscarPorId(id);
        return ResponseEntity.ok().body(empresa);
    }

    @Operation(summary = "Lista empresa por CNPJ")
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaExibirDTO> buscarPorCnpj(@RequestParam String cnpj) {
        return ResponseEntity.ok().body(empresaService.buscarPorCnpjDTO(cnpj));
    }

    @Operation(summary = "Exibe contratos relacionado a uma empresa")
    @GetMapping("/contratos/{id}")
    public Set<ContratoExibirDTO> exibirContratos(@PathVariable Long id) {
        return empresaService.listarContratos(id);
    }

    @Operation(summary = "Criação de uma empresa")
    @PostMapping
    public ResponseEntity<EmpresaExibirDTO> salvar(@RequestBody @Valid EmpresaCreateDTO empresaDTO) {
        Empresa empresa = empresaService.toEntity(empresaDTO);
        empresa = empresaService.salvar(empresa);
        EmpresaExibirDTO response = empresaMapper.entityToExibirDTO(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Edição de uma empresa")
    @PatchMapping("/editar/{id}")
    public ResponseEntity<EmpresaExibirDTO> editar(@PathVariable Long id, @Valid @RequestBody EmpresaPatchDTO empresaPatchDTO) {
        EmpresaExibirDTO empresaExibirDTO = empresaService.editar(id, empresaPatchDTO);
        return ResponseEntity.ok(empresaExibirDTO);
    }

    @Operation(summary = "Deleção lógica de uma empresa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        empresaService.arquivar(id);
        return ResponseEntity.noContent().build();
    }



}

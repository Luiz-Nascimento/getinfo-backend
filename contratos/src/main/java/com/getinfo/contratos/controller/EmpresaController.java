package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaPatchDTO;
import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.mappers.EmpresaMapper;
import com.getinfo.contratos.service.EmpresaService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaMapper empresaMapper;

    @Tag(name = "Empresa", description = "Endpoints para empresas")

    @Operation(summary = "Lista todas empresas cadastradas")
    @GetMapping
    public List<EmpresaExibirDTO> listarTodas() {
        return empresaService.listAllPublic();
    }

    @Tag(name = "Empresa", description = "Endpoints para empresas")
    @Operation(summary = "Lista empresa por id")
    @GetMapping("/id/{id}")
    public ResponseEntity<EmpresaExibirDTO> buscarPorId(@PathVariable Long id) {
        Optional<EmpresaExibirDTO> empresaPublicDTO = empresaService.buscarPorIdPublic(id);
        return empresaPublicDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Tag(name = "Empresa", description = "Endpoints para empresas")
    @Operation(summary = "Lista empresa por CNPJ")
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaExibirDTO> buscarPorCnpj(@RequestParam String cnpj) {
        Optional<EmpresaExibirDTO> empresaExibirDTO = empresaService.buscarPorCnpjDTO(cnpj);
        if (empresaExibirDTO.isPresent()) {
            return ResponseEntity.ok(empresaExibirDTO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Tag(name = "Empresa", description = "Endpoints para empresas")
    @Operation(summary = "Exibe contratos relacionado a uma empresa")
    @GetMapping("/contratos/{id}")
    public Set<ContratoExibirDTO> exibirContratos(Long id) {
        return empresaService.listarContratos(id);
    }

    @Tag(name = "Empresa", description = "Endpoints para empresas")
    @Operation(summary = "Criação de uma empresa")
    @PostMapping
    public ResponseEntity<EmpresaExibirDTO> salvar(@RequestBody @Valid EmpresaCreateDTO empresaDTO) {
        Empresa empresa = empresaService.toEntity(empresaDTO);
        empresa = empresaService.salvar(empresa);
        EmpresaExibirDTO response = empresaMapper.entityToExibirDTO(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Tag(name = "Empresa", description = "Endpoints para empresas")
    @Operation(summary = "Edição de uma empresa")
    @PatchMapping("/editar/{id}")
    public ResponseEntity<EmpresaExibirDTO> editar(@PathVariable Long id, @Valid @RequestBody EmpresaPatchDTO empresaPatchDTO) {
        EmpresaExibirDTO empresaExibirDTO = empresaService.editar(id, empresaPatchDTO);
        return ResponseEntity.ok(empresaExibirDTO);
    }

    @Tag(name = "Empresa", description = "Endpoints para empresas")
    @Operation(summary = "Arquivação de uma empresa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        empresaService.arquivar(id);
        return ResponseEntity.noContent().build();
    }












}

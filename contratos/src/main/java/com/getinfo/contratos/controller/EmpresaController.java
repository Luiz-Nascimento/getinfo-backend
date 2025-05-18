package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.service.EmpresaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<EmpresaExibirDTO> listAllPublic() {
        return empresaService.listAllPublic();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaExibirDTO> buscarPorId(@PathVariable Long id) {
        Optional<EmpresaExibirDTO> empresaPublicDTO = empresaService.buscarPorIdPublic(id);
        if (empresaPublicDTO.isPresent()) {
            return ResponseEntity.ok(empresaPublicDTO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EmpresaExibirDTO> salvar(@RequestBody @Valid EmpresaCreateDTO empresaDTO) {
        Empresa empresa = empresaService.toEntity(empresaDTO);
        empresa = empresaService.salvar(empresa);
        EmpresaExibirDTO response = modelMapper.map(empresa, EmpresaExibirDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpresaExibirDTO> atualizarParcial(@PathVariable Long id ,@RequestBody EmpresaCreateDTO empresaCreateDTO) {
        Empresa empresaAtualizada = empresaService.atualizarParcial(id, empresaCreateDTO);

        return ResponseEntity.ok(new EmpresaExibirDTO(empresaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.buscarPorId(id);
        if (empresa.isPresent()) {
            empresaService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }









}

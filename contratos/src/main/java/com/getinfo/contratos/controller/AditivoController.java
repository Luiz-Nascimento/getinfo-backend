package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.service.AditivoService;
import com.getinfo.contratos.service.EntregavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aditivos")
public class AditivoController {

    @Autowired
    private EntregavelService entregavelService;
    @Autowired
    private AditivoService aditivoService;

    @GetMapping()
    public List<AditivoExibirDTO> findAll() {
        return aditivoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AditivoExibirDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(aditivoService.findById(id));
    }

    @GetMapping("/entregaveis/{id}")
    public List<EntregavelExibirDTO> listarEntregaveis(@PathVariable Long id) {
        return aditivoService.listarEntregaveis(id);
    }


    @PostMapping("/entregavel/{id}")
    public ResponseEntity<EntregavelExibirDTO> adicionarEntregavel(@PathVariable Long id, EntregavelCreateDTO entregavelCreateDTO) {
        return ResponseEntity.ok().body(entregavelService.criarEntregavelAditivo(id, entregavelCreateDTO));
    }
}

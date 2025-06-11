package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.RepactuacaoExibirDTO;
import com.getinfo.contratos.entity.Repactuacao;
import com.getinfo.contratos.service.RepactuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repactuacoes")
public class RepactuacaoController {

    @Autowired
    private RepactuacaoService repactuacaoService;

    @GetMapping
    public List<RepactuacaoExibirDTO> findAll() {
        return repactuacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepactuacaoExibirDTO> findById(@PathVariable Long id) {
        RepactuacaoExibirDTO repactuacaoExibirDTO = repactuacaoService.findById(id);
        return ResponseEntity.ok().body(repactuacaoExibirDTO);
    }
}

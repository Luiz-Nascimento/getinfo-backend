package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.service.EntregavelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entregaveis")
@Tag(name = "Entregáveis", description = "Endpoints para entregável")
public class EntregavelController {

    @Autowired
    private EntregavelService entregavelService;

    @Operation(summary = "Lista todos entregáveis cadastrados no sistema")
    @GetMapping
    public List<EntregavelExibirDTO> findAll() {
        return entregavelService.findAll();
    }
}

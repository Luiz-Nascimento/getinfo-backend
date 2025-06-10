package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.service.EntregavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entregaveis")
public class EntregavelController {

    @Autowired
    private EntregavelService entregavelService;

    @GetMapping
    public List<EntregavelExibirDTO> findAll() {
        return entregavelService.findAll();
    }
}

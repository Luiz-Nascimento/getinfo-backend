package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.service.ContratoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public List<ContratoExibirDTO> listarContratos() {
        return contratoService.listarContratos();
    }

    @PostMapping
    public ResponseEntity<ContratoExibirDTO> criarContrato(@RequestBody ContratoCreateDTO contratoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.criarContrato(contratoCreateDTO));

    }



}

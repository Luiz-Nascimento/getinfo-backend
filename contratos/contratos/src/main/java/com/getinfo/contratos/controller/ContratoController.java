package com.getinfo.contratos.controller;

import com.getinfo.contratos.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;



}

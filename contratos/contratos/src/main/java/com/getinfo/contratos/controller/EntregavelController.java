package com.getinfo.contratos.controller;

import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.service.EntregavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entregaveis")
public class EntregavelController {

    @Autowired
    private EntregavelService entregavelService;


}

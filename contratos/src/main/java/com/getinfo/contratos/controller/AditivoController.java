package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.repository.AditivoRepository;
import com.getinfo.contratos.service.AditivoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.util.List;

@RestController
@RequestMapping("/aditivos")
public class AditivoController {

    @Autowired
    private AditivoService aditivoService;

    @GetMapping
    public List<AditivoExibirDTO> findAll() {
        return aditivoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AditivoExibirDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(aditivoService.findById(id));
    }

}

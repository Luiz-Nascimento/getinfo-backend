package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.service.AditivoService;
import com.getinfo.contratos.service.EntregavelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/aditivos")
@Tag(name = "Aditivo", description = "Endpoints para aditivos")
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

    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewAnexo(@PathVariable Long id) {
        byte[] anexo = aditivoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=anexoAditivo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAnexo(@PathVariable Long id) {
        byte[] anexo = aditivoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=anexoAditivo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }



    @PostMapping("/entregavel/{id}")
    public ResponseEntity<EntregavelExibirDTO> adicionarEntregavel(@PathVariable Long id, EntregavelCreateDTO entregavelCreateDTO) {
        return ResponseEntity.ok().body(entregavelService.criarEntregavelAditivo(id, entregavelCreateDTO));
    }

    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadAnexo(@PathVariable Long id, @RequestParam("anexo")MultipartFile anexo) {
        aditivoService.uploadAnexo(id, anexo);
        return ResponseEntity.noContent().build();
    }
}

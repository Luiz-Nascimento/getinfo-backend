package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.RepactuacaoExibirDTO;
import com.getinfo.contratos.entity.Repactuacao;
import com.getinfo.contratos.service.RepactuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewAnexo(@PathVariable Long id) {
        byte[] anexo = repactuacaoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=anexoRepactuacao.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAnexo(@PathVariable Long id) {
        byte[] anexo = repactuacaoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=anexoRepactuacao.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }
    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadAnexo(@PathVariable Long id, @RequestParam("anexo") MultipartFile anexo) {
        repactuacaoService.uploadAnexo(id, anexo);
        return ResponseEntity.noContent().build();
    }



}

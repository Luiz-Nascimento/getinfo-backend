package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.service.ContratoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    public List<ContratoExibirDTO> listarContratos() {
        return contratoService.listarContratos();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAnexo(@PathVariable Long id) {
        byte[] anexo = contratoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=anexo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }

    @PostMapping
    public ResponseEntity<ContratoExibirDTO> criarContrato(@RequestBody ContratoCreateDTO contratoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.criarContrato(contratoCreateDTO));

    }

    @PostMapping("/{contratoId}/colaboradores")
    public ResponseEntity<Void> adicionarColabores(@PathVariable Long contratoId
            , @RequestBody Set<Long> idColaboradores) {
        contratoService.adicionarColaboradores(contratoId, idColaboradores);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarContrato(@PathVariable Long id) {
        contratoService.ativar(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> atualizarAnexo(@PathVariable Long id, @RequestParam("anexo") MultipartFile anexo) {
        contratoService.atualizarAnexo(id, anexo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/arquivar/{id}")
    public ResponseEntity<Void> arquivarContrato(@PathVariable Long id) {
        contratoService.arquivar(id);
        return ResponseEntity.noContent().build();
    }



}

package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.*;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.service.ContratoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
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
    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @GetMapping
    public List<ContratoExibirDTO> listarContratos() {
        return contratoService.listarContratos();
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @GetMapping("/{id}")
    public ResponseEntity<ContratoExibirDTO> buscarPorId(@PathVariable Long id) {
        ContratoExibirDTO contrato = contratoService.buscarPorId(id);
        return ResponseEntity.ok().body(contrato);
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @GetMapping("/agregados/{id}")
    public Set<AgregadoExibirDTO> listarAgregados(@PathVariable Long id) {
        return contratoService.exibirAgregados(id);
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @GetMapping("/entregaveis/{id}")
    public List<EntregavelExibirDTO> exibirEntregaveis(@PathVariable Long id) {
        return contratoService.exibirEntregaveis(id);
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @GetMapping("/aditivos/{id}")
    public List<AditivoExibirDTO> exibirAditivos(@PathVariable Long id) {
        return contratoService.exibirAditivos(id);
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
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

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PostMapping
    public ResponseEntity<ContratoExibirDTO> criarContrato(@RequestBody @Valid ContratoCreateDTO contratoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.criarContrato(contratoCreateDTO));

    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PostMapping("/agregados/")
    public ResponseEntity<Void> adicionarAgregado(@RequestBody AgregadoCreateDTO agregadoCreateDTO) {
        contratoService.adicionarAgregado(agregadoCreateDTO);
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PostMapping("/aditivar/{id}")
    public ResponseEntity<ContratoExibirDTO> aditivar(@PathVariable Long id, AditivoCreateDTO aditivo) {
        return ResponseEntity.ok().body(contratoService.aditivar(id, aditivo));
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PostMapping("/entregavel/{id}")
    public ResponseEntity<EntregavelExibirDTO> criarEntregavel(@PathVariable Long id, EntregavelCreateDTO dto) {
        return ResponseEntity.ok().body(contratoService.criarEntregavel(id, dto));
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PatchMapping("/editar/{id}")
    public ResponseEntity<ContratoExibirDTO> editarContrato(@PathVariable Long id, @RequestBody ContratoPatchDTO contratoPatchDTO) {
        ContratoExibirDTO contratoExibirDTO = contratoService.editarContrato(id, contratoPatchDTO);
        return ResponseEntity.ok().body(contratoExibirDTO);
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarContrato(@PathVariable Long id) {
        contratoService.ativar(id);
        return ResponseEntity.ok().build();
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @PatchMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> atualizarAnexo(@PathVariable Long id, @RequestParam("anexo") MultipartFile anexo) {
        contratoService.atualizarAnexo(id, anexo);
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "Contrato", description = "Endpoints para contratos")
    @DeleteMapping("/arquivar/{id}")
    public ResponseEntity<Void> arquivarContrato(@PathVariable Long id) {
        contratoService.arquivar(id);
        return ResponseEntity.noContent().build();
    }



}

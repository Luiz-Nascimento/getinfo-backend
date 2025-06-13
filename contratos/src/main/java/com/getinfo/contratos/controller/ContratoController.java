package com.getinfo.contratos.controller;

import com.getinfo.contratos.DTOs.*;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.entity.Repactuacao;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.service.ContratoService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Contrato", description = "Endpoints para contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @Operation(summary = "Lista todos contratos cadastrados",
            description = "Retorna uma lista com as informações de todos os contratos cadastrados no sistema.")
    @GetMapping
    public List<ContratoExibirDTO> listarContratos() {
        return contratoService.listarContratos();
    }
    @Operation(summary = "Exibe o contrato com o ID especificado",
            description = "Retorna um DTO do contrato com o ID especificado" )
    @GetMapping("/{id}")
    public ResponseEntity<ContratoExibirDTO> buscarPorId(@PathVariable Long id) {
        ContratoExibirDTO contrato = contratoService.buscarPorId(id);
        return ResponseEntity.ok().body(contrato);
    }
    @Operation(summary = "Exibe todos agregados relacionado ao contrato",
            description = "Retorna um set com todos agregados do contrato do ID especificado." )
    @GetMapping("/agregados/{id}")
    public Set<AgregadoExibirDTO> listarAgregados(@PathVariable Long id) {
        return contratoService.exibirAgregados(id);
    }

    @Operation(summary = "Exibe todos entregáveis relacionados a um contrato específico",
            description = "Retorna uma lista com todos entregáveis relacionados ao contrato do ID especificado." )
    @GetMapping("/entregaveis/{id}")
    public List<EntregavelExibirDTO> exibirEntregaveis(@PathVariable Long id) {
        return contratoService.exibirEntregaveis(id);
    }

    @Operation(summary = "Exibe todos aditivos relacionados ao contrato",
            description = "Retorna uma lista com todos entregáveis relacionados ao contrato do ID especificado.")
    @GetMapping("/aditivos/{id}")
    public List<AditivoExibirDTO> exibirAditivos(@PathVariable Long id) {
        return contratoService.exibirAditivos(id);
    }

    @Operation(summary = "Exibe todas repactuações relacionados ao contrato",
            description = "Retorna uma lista com todas repactuações relacionadas ao contrato do ID especificado")
    @GetMapping("/repactuacoes/{id}")
    public List<RepactuacaoExibirDTO> exibirRepactuacoes(@PathVariable Long id) {
        return contratoService.exibirRepactuacoes(id);
    }

    @Operation(summary = "Realiza o download do anexo do documento do contrato",
            description = "Faz o download do PDF do anexo do documento do contrato do ID especificado.")
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAnexo(@PathVariable Long id) {
        byte[] anexo = contratoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=anexoContrato.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }

    @Operation(summary = "Realiza a visualização do documento do contrato",
            description = "Retorna a visualização dos dados brutos do pdf")
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewAnexo(@PathVariable Long id) {
        byte[] anexo = contratoService.obterAnexo(id);
        if (anexo == null || anexo.length == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=anexoContrato.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(anexo);
    }

    @Operation(summary = "Realiza a criação de um contrato",
            description = "Faz a criação de um contrato apartir dos campos preenchidos do DTO de criaçao de contrato")
    @PostMapping
    public ResponseEntity<ContratoExibirDTO> criarContrato(@RequestBody @Valid ContratoCreateDTO contratoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoService.criarContrato(contratoCreateDTO));

    }

    @Operation(summary = "Cria um novo agregado relacionado a um contrato",
            description = "Faz a criação de um novo agregado apartir das informações de um colaborador do ID especificado" +
                    " e o relaciona com um contrato de ID especificado e um campo de novo cargo")
    @PostMapping("/agregados/")
    public ResponseEntity<Void> adicionarAgregado(@RequestBody AgregadoCreateDTO agregadoCreateDTO) {
        contratoService.adicionarAgregado(agregadoCreateDTO);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Aditiva um contrato e armazena as informações desse aditivo",
            description = "Cria um novo aditivo relacionado a um contrato e aditiva esse contrato baseado nos campos do aditivo")
    @PostMapping("/aditivar/{id}")
    public ResponseEntity<AditivoExibirDTO> aditivar(@PathVariable Long id, @RequestBody AditivoCreateDTO aditivo) {
        AditivoExibirDTO aditivoExibirDTO = contratoService.aditivar(id, aditivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(aditivoExibirDTO);
    }

    @Operation(summary = "Cria uma repactuação e armazena as informações dela em um contrato",
            description = "Faz a criação de uma repactuação e armazena dentro de um contrato")
    @PostMapping("/repactuar/{id}")
    public ResponseEntity<RepactuacaoExibirDTO> repactuar(@PathVariable Long id, @RequestBody RepactuacaoCreateDTO repactuacaoCreateDTO) {
        RepactuacaoExibirDTO repactuacaoExibirDTO = contratoService.repactuar(id, repactuacaoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(repactuacaoExibirDTO);
    }

    @Operation(summary = "Faz a criação de um entregável dentro de um contrato")
    @PostMapping("/entregavel/{id}")
    public ResponseEntity<EntregavelExibirDTO> criarEntregavel(@PathVariable Long id, @RequestBody EntregavelCreateDTO dto) {
        EntregavelExibirDTO entregavelExibirDTO = contratoService.criarEntregavel(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entregavelExibirDTO);
    }

    @Operation(summary = "Faz a atualização parcial ou completa de informações do contrato")
    @PatchMapping("/editar/{id}")
    public ResponseEntity<ContratoExibirDTO> editarContrato(@PathVariable Long id, @RequestBody ContratoPatchDTO contratoPatchDTO) {
        ContratoExibirDTO contratoExibirDTO = contratoService.editarContrato(id, contratoPatchDTO);
        return ResponseEntity.ok().body(contratoExibirDTO);
    }

    @Operation(summary = "Muda o status do contrato para ativo")
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarContrato(@PathVariable Long id) {
        contratoService.ativar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Faz upload de pdf do contrato")
    @PatchMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> atualizarAnexo(@PathVariable Long id, @RequestParam("anexo") MultipartFile anexo) {
        contratoService.atualizarAnexo(id, anexo);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Muda status de contrato para arquivado")
    @DeleteMapping("/arquivar/{id}")
    public ResponseEntity<Void> arquivarContrato(@PathVariable Long id) {
        contratoService.arquivar(id);
        return ResponseEntity.noContent().build();
    }



}

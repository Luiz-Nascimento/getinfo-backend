package com.getinfo.contratos.service;


import com.getinfo.contratos.DTOs.ColaboradorExibirDTO;
import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.entity.Colaborador;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.enums.ColaboradorStatus;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.mappers.ColaboradorMapper;
import com.getinfo.contratos.mappers.ContratoMapper;
import com.getinfo.contratos.repository.ColaboradorRepository;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ContratoMapper contratoMapper;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ColaboradorMapper colaboradorMapper;


    public List<ContratoExibirDTO> listarContratos() {
        List<ContratoExibirDTO> contratoExibirDTOS = new ArrayList<>();
        for (Contrato contrato:  contratoRepository.findAll()) {
            contratoExibirDTOS.add(new ContratoExibirDTO(
                    contrato.getId(),
                    contrato.getEmpresa().getNomeFantasia(),
                    contrato.getEmpresa().getCnpj(),// pega só o nome fantasia da empresa
                    contrato.getStatus(),
                    contrato.getValor(),
                    contrato.getDescricao(),
                    contrato.getTipo(),
                    contrato.getNomeResponsavel()
            ));
        }
        return contratoExibirDTOS;
    }
    public ContratoExibirDTO buscarPorId(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado!"));
        return contratoMapper.entityToDTO(contrato);
    }

    public List<ColaboradorExibirDTO> exibirAgregados(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
        List<ColaboradorExibirDTO> colaboradorExibir = new ArrayList<>();
        for (Colaborador colaborador: contrato.getColaboradores()) {
            colaboradorExibir.add(colaboradorMapper.entityToExibirDTO(colaborador));
        }
        return colaboradorExibir;
    }

    public byte[] obterAnexo(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
        byte[] anexo = contrato.getAnexo();
        return anexo;
    }


    public ContratoExibirDTO criarContrato(ContratoCreateDTO contratoCreateDTO) {
        String cnpjSanitizado = empresaService.sanitizarCnpj(contratoCreateDTO.cnpj());
        Empresa empresa = empresaRepository.findByCnpj(cnpjSanitizado)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));


        Contrato contrato = contratoMapper.createDtoToEntity(contratoCreateDTO);
        contrato.setEmpresa(empresa);
        contratoRepository.save(contrato);
        return new ContratoExibirDTO(
                contrato.getId(),
                contrato.getEmpresa().getNomeFantasia(),
                contrato.getEmpresa().getCnpj(),// pega só o nome fantasia da empresa
                contrato.getStatus(),
                contrato.getValor(),
                contrato.getDescricao(),
                contrato.getTipo(),
                contrato.getNomeResponsavel()
        );
    }

    public void deletar(Long id) {
        contratoRepository.deleteById(id);
    }



    @Transactional
    public void atualizarAnexo(Long id, MultipartFile anexo) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));

        if (anexo == null || anexo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo não enviado ou está vazio");
        }
        if (!"application/pdf".equals(anexo.getContentType())) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado. Envie um PDF.");
        }

        try {
            contrato.setAnexo(anexo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo enviado", e);
        }

    }

    @Transactional
    public void adicionarColaboradores(Long contratoId, Set<Long> idColaboradores) {
        Contrato contrato = contratoRepository.findById(contratoId)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));

        Set<Colaborador> colaboradoresParaAdd = new HashSet<>(colaboradorRepository.findAllById(idColaboradores));

        Set<Long> encontrados = colaboradoresParaAdd.stream()
                .map(Colaborador::getId)
                .collect(Collectors.toSet());
        Set<Long> naoEncontrados = new HashSet<>(idColaboradores);
        naoEncontrados.removeAll(encontrados);

        if (!naoEncontrados.isEmpty()) {
            throw new EntityNotFoundException("Colaboradores não encontrados: " + naoEncontrados);
        }


        for (Colaborador colaborador: colaboradoresParaAdd) {
            contrato.getColaboradores().add(colaborador);
            colaborador.getContratos().add(contrato);
        }
    }



    @Transactional
    public void arquivar(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado!"));
        if (contrato.getStatus().equals(StatusContrato.ARQUIVADO)) {
            throw new IllegalStateException("Contrato já está arquivado");
        }
        contrato.setStatus(StatusContrato.ARQUIVADO);
    }

    @Transactional
    public void ativar(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado!"));
        if (contrato.getStatus().equals(StatusContrato.ATIVO) ) {
            throw new IllegalStateException("Contrato já está ativo");
        }
        contrato.setStatus(StatusContrato.ATIVO);
    }

}

package com.getinfo.contratos.service;


import com.getinfo.contratos.DTOs.*;
import com.getinfo.contratos.entity.*;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.mappers.AgregadoMapper;
import com.getinfo.contratos.mappers.ColaboradorMapper;
import com.getinfo.contratos.mappers.ContratoMapper;
import com.getinfo.contratos.mappers.EntregavelMapper;
import com.getinfo.contratos.repository.AgregadoRepository;
import com.getinfo.contratos.repository.ColaboradorRepository;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    private AgregadoRepository agregadoRepository;
    @Autowired
    private ContratoMapper contratoMapper;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private ColaboradorMapper colaboradorMapper;
    @Autowired
    private EntregavelMapper entregavelMapper;
    @Autowired
    private AgregadoMapper agregadoMapper;

    public Contrato acharPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Contrato não encontrado"));
    }

    public List<ContratoExibirDTO> listarContratos() {
        List<ContratoExibirDTO> contratoExibirDTOS = new ArrayList<>();
        for (Contrato contrato:  contratoRepository.findAll()) {
            contratoExibirDTOS.add(contratoMapper.entityToDTO(contrato));
        }
        return contratoExibirDTOS;
    }
    public ContratoExibirDTO buscarPorId(Long id) {
        Contrato contrato = acharPorId(id);
        return contratoMapper.entityToDTO(contrato);
    }

    public Set<AgregadoExibirDTO> exibirAgregados(Long contratoId) {
        Contrato contrato = contratoRepository.findById(contratoId)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
        return agregadoMapper.toExibirDTOSet(contrato.getAgregados());

    }

    public List<EntregavelExibirDTO> exibirEntregaveis(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
        List<EntregavelExibirDTO> entregaveisExibir = new ArrayList<>();
        for (Entregavel entregavel: contrato.getEntregaveis()) {
            entregaveisExibir.add(entregavelMapper.toDto(entregavel));
        }
        return entregaveisExibir;
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


        Contrato contrato = contratoMapper.createDtoToEntity(contratoCreateDTO, empresa);
        contratoRepository.save(contrato);
        return  contratoMapper.entityToDTO(contrato);
    }

    @Transactional
    public ContratoExibirDTO editarContrato(Long id, ContratoPatchDTO contratoPatchDTO) {
        Contrato contrato = acharPorId(id);
        contratoMapper.patchContratoFromDto(contratoPatchDTO, contrato);
        contratoRepository.save(contrato);
        return contratoMapper.entityToDTO(contrato);
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
    public void adicionarAgregado(AgregadoCreateDTO agregadoCreateDTO) {
        Colaborador colaborador = colaboradorRepository.findById(agregadoCreateDTO.idColaborador())
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));
        Contrato contrato = contratoRepository.findById(agregadoCreateDTO.idContrato())
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));

        Agregado agregado = new Agregado();
        agregado.setCargoContrato(agregadoCreateDTO.cargoContrato());
        agregado.setColaborador(colaborador);
        agregado.setContrato(contrato);
        contrato.adicionarAgregado(agregado);
        colaborador.adicionarAgregado(agregado);
        agregadoRepository.save(agregado);
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

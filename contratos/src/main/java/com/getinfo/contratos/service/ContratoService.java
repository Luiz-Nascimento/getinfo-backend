package com.getinfo.contratos.service;


import com.getinfo.contratos.DTOs.*;
import com.getinfo.contratos.entity.*;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.mappers.*;
import com.getinfo.contratos.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

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
    private EntregavelRepository entregavelRepository;
    @Autowired
    private EntregavelMapper entregavelMapper;
    @Autowired
    private AgregadoMapper agregadoMapper;

    @Autowired
    private AditivoMapper aditivoMapper;

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

    public List<AditivoExibirDTO> exibirAditivos(Long id) {
        Contrato contrato = acharPorId(id);
        List<AditivoExibirDTO> aditivosDTO = new ArrayList<>();
        for (Aditivo aditivo: contrato.getAditivos()) {
            aditivosDTO.add(aditivoMapper.toExibirDTO(aditivo));
        }
        return aditivosDTO;
    }

    public byte[] obterAnexo(Long id) {
        Contrato contrato = acharPorId(id);
        return contrato.getAnexo();
    }

    @Transactional
    public ContratoExibirDTO criarContrato(ContratoCreateDTO contratoCreateDTO) {
        String cnpjSanitizado = empresaService.sanitizarCnpj(contratoCreateDTO.cnpj());
        Empresa empresa = empresaRepository.findByCnpj(cnpjSanitizado)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));
        Contrato contrato = contratoMapper.createDtoToEntity(contratoCreateDTO, empresa);
        contratoRepository.save(contrato);
        empresa.getContratos().add(contrato);
        return  contratoMapper.entityToDTO(contrato);
    }

    @Transactional
    public ContratoExibirDTO editarContrato(Long id, ContratoPatchDTO contratoPatchDTO) {
        Contrato contrato = acharPorId(id);
        contratoMapper.patchContratoFromDto(contratoPatchDTO, contrato);
        contratoRepository.save(contrato);
        return contratoMapper.entityToDTO(contrato);
    }

    @Transactional
    public ContratoExibirDTO aditivar(Long idContrato, AditivoCreateDTO aditivoDto) {
        Contrato contrato = acharPorId(idContrato);
        Aditivo aditivo = aditivoMapper.toEntity(aditivoDto);
        aditivo.setContrato(contrato);
        contrato.setValor(contrato.getValor().add(aditivoDto.valorAditivo()));
        contrato.setDataFim(contrato.getDataFim().plusDays(aditivoDto.diasAditivo()));
        contrato.getAditivos().add(aditivo);
        return contratoMapper.entityToDTO(contrato);
    }


    @Transactional
    public EntregavelExibirDTO criarEntregavel(Long contratoId, EntregavelCreateDTO dto) {
        Entregavel entregavel = entregavelMapper.fromDto(dto);
        Contrato contrato = acharPorId(contratoId);
        entregavel.setContrato(contrato);
        entregavelRepository.save(entregavel);
        contrato.getEntregaveis().add(entregavel);
        return entregavelMapper.toDto(entregavel);
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

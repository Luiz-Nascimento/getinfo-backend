package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.mappers.EntregavelMapper;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.repository.EntregavelRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregavelService {

    @Autowired
    private EntregavelRepository entregavelRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private EntregavelMapper entregavelMapper;

    @Transactional
    public EntregavelExibirDTO criarEntregavel(EntregavelCreateDTO dto) {
        Entregavel entregavel = entregavelMapper.fromDto(dto);
        Contrato contrato = contratoRepository.findById(dto.contratoId())
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
        entregavel.setContrato(contrato);
        entregavelRepository.save(entregavel);
        contrato.getEntregaveis().add(entregavel);
        return entregavelMapper.toDto(entregavel);
    }

    public List<EntregavelExibirDTO> listarTodos() {
        return entregavelRepository.findAll().stream()
                .map(entregavelMapper::toDto)
                .toList();
    }

    public void deletar(Long id) {
        entregavelRepository.deleteById(id);
    }

}
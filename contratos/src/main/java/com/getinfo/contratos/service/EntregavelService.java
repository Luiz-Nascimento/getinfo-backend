package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.mappers.AditivoMapper;
import com.getinfo.contratos.mappers.EntregavelMapper;
import com.getinfo.contratos.repository.AditivoRepository;
import com.getinfo.contratos.repository.EntregavelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntregavelService {

    @Autowired
    private EntregavelRepository entregavelRepository;

    @Autowired
    private EntregavelMapper entregavelMapper;

    @Autowired
    private AditivoRepository aditivoRepository;
    @Autowired
    private AditivoMapper aditivoMapper;
    @Autowired
    private ContratoService contratoService;

    public List<EntregavelExibirDTO> findAll() {
        List<EntregavelExibirDTO> entregaveis = new ArrayList<>();
        for (Entregavel entregavel: entregavelRepository.findAll()) {
            entregaveis.add(entregavelMapper.toDto(entregavel));
        }
        return entregaveis;
    }

    @Transactional
    public EntregavelExibirDTO criarEntregavelAditivo(Long idAditivo, EntregavelCreateDTO entregavelCreateDTO) {
        Aditivo aditivo = aditivoRepository.findById(idAditivo)
                .orElseThrow(()-> new EntityNotFoundException("Aditivo não encontrado"));
        Entregavel entregavel = entregavelMapper.fromDto(entregavelCreateDTO);

        Contrato contrato = contratoService.acharPorId(aditivo.getContrato().getId());
        entregavel.setContrato(contrato);
        contrato.getEntregaveis().add(entregavel);
        aditivo.getEntregaveis().add(entregavel);
        entregavelRepository.save(entregavel);
        return entregavelMapper.toDto(entregavel);
    }
}

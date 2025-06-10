package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.mappers.AditivoMapper;
import com.getinfo.contratos.mappers.EntregavelMapper;
import com.getinfo.contratos.repository.AditivoRepository;
import com.getinfo.contratos.repository.EntregavelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public EntregavelExibirDTO criarEntregavelAditivo(Long idAditivo, EntregavelCreateDTO entregavelCreateDTO) {
        Aditivo aditivo = aditivoRepository.findById(idAditivo)
                .orElseThrow(()-> new EntityNotFoundException("Aditivo não encontrado"));
        Entregavel entregavel = entregavelMapper.fromDto(entregavelCreateDTO);
        entregavelRepository.save(entregavel);
        aditivo.getEntregaveis().add(entregavel);
        return entregavelMapper.toDto(entregavel);
    }
}

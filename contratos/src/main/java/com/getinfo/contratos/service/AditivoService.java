package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.mappers.AditivoMapper;
import com.getinfo.contratos.mappers.EntregavelMapper;
import com.getinfo.contratos.repository.AditivoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AditivoService {

    @Autowired
    private AditivoRepository aditivoRepository;

    @Autowired
    private AditivoMapper aditivoMapper;

    @Autowired
    private EntregavelMapper entregavelMapper;

    public List<AditivoExibirDTO> findAll() {
        List<AditivoExibirDTO> listAditivos = new ArrayList<>();
        for (Aditivo aditivo: aditivoRepository.findAll()) {
            listAditivos.add(aditivoMapper.toExibirDTO(aditivo));
        }
        return listAditivos;
    }

    public AditivoExibirDTO findById(Long id) {
        return aditivoMapper.toExibirDTO(aditivoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aditivo não encontrado")));
    }


    public List<EntregavelExibirDTO> listarEntregaveis(Long id) {
        Aditivo aditivo = aditivoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aditivo não encontrado"));
        List<EntregavelExibirDTO> entregaveis = new ArrayList<>();
        for (Entregavel entregavel: aditivo.getEntregaveis()) {
            entregaveis.add(entregavelMapper.toDto(entregavel));
        }
        return entregaveis;
    }


}

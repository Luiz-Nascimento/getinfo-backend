package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.RepactuacaoExibirDTO;
import com.getinfo.contratos.entity.Repactuacao;
import com.getinfo.contratos.mappers.RepactuacaoMapper;
import com.getinfo.contratos.repository.RepactuacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RepactuacaoService {

    @Autowired
    private RepactuacaoRepository repactuacaoRepository;

    @Autowired
    private RepactuacaoMapper repactuacaoMapper;

    public List<RepactuacaoExibirDTO> findAll() {
        List<RepactuacaoExibirDTO> repactuacoes = new ArrayList<>();
        for (Repactuacao repactuacao: repactuacaoRepository.findAll()) {
            repactuacoes.add(repactuacaoMapper.toDto(repactuacao));
        }
        return repactuacoes;
    }

    public RepactuacaoExibirDTO findById(Long id) {
        Repactuacao repactuacao = repactuacaoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Repactuação não encontrada."));
        return repactuacaoMapper.toDto(repactuacao);
    }


}

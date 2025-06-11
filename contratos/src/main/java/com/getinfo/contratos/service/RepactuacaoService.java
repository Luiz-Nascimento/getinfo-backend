package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.RepactuacaoExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.entity.Repactuacao;
import com.getinfo.contratos.mappers.RepactuacaoMapper;
import com.getinfo.contratos.repository.RepactuacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public byte[] obterAnexo(Long id) {
        Repactuacao repactuacao = repactuacaoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Repactuação não encontrada."));
        return repactuacao.getAnexo();
    }

    @Transactional
    public void uploadAnexo(Long id, MultipartFile anexo) {
        Repactuacao repactuacao = repactuacaoRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Repactuacao não encontrada"));

        if (anexo == null || anexo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo não enviado ou está vazio");
        }
        if (!"application/pdf".equals(anexo.getContentType())) {
            throw new IllegalArgumentException("Tipo de arquivo não suportado. Envie um PDF.");
        }
        try {
            repactuacao.setAnexo(anexo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo enviado", e);
        }
    }


}

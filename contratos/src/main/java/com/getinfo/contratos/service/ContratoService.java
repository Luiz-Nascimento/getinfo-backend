package com.getinfo.contratos.service;


import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoUpdateDTO;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.mappers.ContratoMapper;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final EmpresaRepository empresaRepository;
    private final ContratoMapper contratoMapper;

    public ContratoService(ContratoRepository contratoRepository,
                           EmpresaRepository empresaRepository,
                           ContratoMapper contratoMapper) {
        this.contratoRepository = contratoRepository;
        this.empresaRepository = empresaRepository;
        this.contratoMapper = contratoMapper;
    }

    public List<Contrato> listarTodas() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> buscarPorId(Long id) {
        return contratoRepository.findById(id);
    }

    public Contrato salvarComDTO(ContratoCreateDTO dto) {
        Contrato contrato = contratoMapper.createDTOtoEntity(dto);

        contrato.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada")));

        return contratoRepository.save(contrato);
    }

    public Contrato atualizar(Long id, ContratoUpdateDTO dto) {
        Contrato existente = contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));

        Contrato atualizado = contratoMapper.updateDTOtoEntity(dto);

        atualizado.setId(id);
        atualizado.setEmpresa(empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada")));

        return contratoRepository.save(atualizado);
    }


    public void deletar(Long id) {
        contratoRepository.deleteById(id);
    }

}

package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.ColaboradorCreateDTO;
import com.getinfo.contratos.DTOs.ColaboradorExibirDTO;
import com.getinfo.contratos.DTOs.ColaboradorPatchDTO;
import com.getinfo.contratos.entity.Colaborador;
import com.getinfo.contratos.enums.ColaboradorStatus;
import com.getinfo.contratos.mappers.ColaboradorMapper;
import com.getinfo.contratos.repository.ColaboradorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorMapper colaboradorMapper;


    public List<Colaborador> listarTodas() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> buscarPorId(Long id) {
        return colaboradorRepository.findById(id);
    }

    public List<ColaboradorExibirDTO> listarDTOs() {
        List<Colaborador> colaboradores = listarTodas();
        List<ColaboradorExibirDTO> colaboradoresDTO = new ArrayList<>();
        for (Colaborador colaborador: colaboradores) {
            colaboradoresDTO.add(colaboradorMapper.entityToExibirDTO(colaborador));
        }
        return colaboradoresDTO;
    }

    public Optional<ColaboradorExibirDTO> buscarPorIdDTO(Long id) {
        return colaboradorMapper.optionalEntityToOptionalExibirDTO(buscarPorId(id));
    }

    public ColaboradorExibirDTO atualizarParcial(Long id, ColaboradorPatchDTO colaboradorPatchDTO) {
        Optional<Colaborador> colaboradorOpt = buscarPorId(id);
        if (colaboradorOpt.isPresent()) {
            Colaborador colaborador = colaboradorOpt.get();
            if (colaboradorPatchDTO.nome() != null) {
                colaborador.setNome(colaboradorPatchDTO.nome());
            }
            if (colaboradorPatchDTO.sobrenome() != null) {
                colaborador.setSobrenome(colaboradorPatchDTO.sobrenome());
            }
            if (colaboradorPatchDTO.email() != null) {
                colaborador.setEmail(colaboradorPatchDTO.email());
            }
            if (colaboradorPatchDTO.telefone() != null) {
                colaborador.setTelefone(colaboradorPatchDTO.telefone());
            }
            if (colaboradorPatchDTO.cargo() != null) {
                colaborador.setCargo(colaboradorPatchDTO.cargo());
            }
            if (colaboradorPatchDTO.status() != null) {
                colaborador.setStatus(colaboradorPatchDTO.status());
            }

            salvar(colaborador);
            ColaboradorExibirDTO colaboradorExibirDTO = colaboradorMapper.entityToExibirDTO(colaborador);
            return colaboradorExibirDTO;
        }
        return null;
    }
    @Transactional
    public void desativar(Long id) {
        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));
        if (colaborador.getStatus().equals(ColaboradorStatus.INATIVO)) {
            throw new IllegalStateException("Colaborador já está desligado.");
        }
        colaborador.setStatus(ColaboradorStatus.INATIVO);
    }




    public Colaborador CreateDTOtoEntity(ColaboradorCreateDTO colaboradorDTO) {
        return colaboradorMapper.createDTOtoEntity(colaboradorDTO);
    }

    public ColaboradorExibirDTO entityToExibirDTO(Colaborador colaborador) {
        return colaboradorMapper.entityToExibirDTO(colaborador);
    }
    public Colaborador salvar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public void deletar(Long id) {
        colaboradorRepository.deleteById(id);
    }
}

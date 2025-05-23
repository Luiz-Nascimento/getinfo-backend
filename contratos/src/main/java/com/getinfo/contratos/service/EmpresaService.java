package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.mappers.EmpresaMapper;
import com.getinfo.contratos.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaMapper empresaMapper;

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public List<EmpresaExibirDTO> listAllPublic() {
        List<EmpresaExibirDTO> empresaExibirDTOS = new ArrayList<>();
        for(Empresa empresa: listarTodas()) {
            empresaExibirDTOS.add(empresaMapper.entityToExibirDTO(empresa));

        }
        return empresaExibirDTOS;
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Optional<Empresa> buscarPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
    }

    public String sanitizarCnpj(String cnpj) {
        return cnpj.replaceAll("\\D", "");
    }


    public Optional<EmpresaExibirDTO> buscarPorCnpjDTO(String cnpj) {
        cnpj = sanitizarCnpj(cnpj);
        Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);
        return empresaMapper.optionalEntityToOptionalExibirDTO(empresa);
    }

    public Optional<EmpresaExibirDTO> buscarPorIdPublic(Long id) {
        Optional<Empresa> empresa = buscarPorId(id);
        return empresaMapper.optionalEntityToOptionalExibirDTO(empresa);

    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public Empresa toEntity(EmpresaCreateDTO empresaDTO) {
        return empresaMapper.createDTOtoEntity(empresaDTO);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }

    @Transactional
    public void deletarLogico(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isEmpty()) {
            throw new EntityNotFoundException("Empresa não encontrada");
        }
        empresa.get().setAtivo(false);
        empresaRepository.save(empresa.get());
    }




}

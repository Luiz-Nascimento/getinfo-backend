package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaPatchDTO;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.mappers.ContratoMapper;
import com.getinfo.contratos.mappers.EmpresaMapper;
import com.getinfo.contratos.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaMapper empresaMapper;

    @Autowired
    private ContratoMapper contratoMapper;

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

    public Set<ContratoExibirDTO> listarContratos(Long idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(()-> new EntityNotFoundException("Empresa não encontrada"));
        Set<ContratoExibirDTO> contratos = new HashSet<>();
        for (Contrato contrato: empresa.getContratos()) {
            contratos.add(contratoMapper.entityToDTO(contrato));
        }
        return contratos;
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

    @Transactional
    public EmpresaExibirDTO editar(Long id, EmpresaPatchDTO empresaPatchDTO) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada."));

        empresaMapper.patchEmpresaFromDto(empresaPatchDTO, empresa);
        empresaRepository.save(empresa);
        return empresaMapper.entityToExibirDTO(empresa);

    }

    public Empresa toEntity(EmpresaCreateDTO empresaDTO) {
        EmpresaCreateDTO dtoSanitizado = new EmpresaCreateDTO(
                sanitizarCnpj(empresaDTO.cnpj()),
                empresaDTO.razaoSocial(),
                empresaDTO.nomeFantasia(),
                empresaDTO.tipo(),
                empresaDTO.cep(),
                empresaDTO.logradouro(),
                empresaDTO.bairro(),
                empresaDTO.numero(),
                empresaDTO.estado(),
                empresaDTO.cidade(),
                empresaDTO.complemento(),
                empresaDTO.email(),
                empresaDTO.telefone(),
                empresaDTO.nomeResponsavel(),
                empresaDTO.emailResponsavel(),
                empresaDTO.telefoneResponsavel(),
                empresaDTO.cpfResponsavel()
        );
        return empresaMapper.createDTOtoEntity(dtoSanitizado);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }
    @Transactional
    public void arquivar(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));
        if (!empresa.getAtivo()) {
            throw new IllegalStateException("Empresa já arquivada!");
        }
        empresa.setAtivo(false);
    }






}

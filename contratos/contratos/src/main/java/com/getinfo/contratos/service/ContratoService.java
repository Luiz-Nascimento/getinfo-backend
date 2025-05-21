package com.getinfo.contratos.service;


import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.repository.ContratoRepository;
import com.getinfo.contratos.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;


    public List<Contrato> listarTodas() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> buscarPorId(Long id) {
        return contratoRepository.findById(id);
    }

    public Contrato salvar(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    public void deletar(Long id) {
        contratoRepository.deleteById(id);
    }

}

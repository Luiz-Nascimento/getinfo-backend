package com.getinfo.contratos.service;

import com.getinfo.contratos.entity.Entregavel;
import com.getinfo.contratos.repository.EntregavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregavelService {

    @Autowired
    private EntregavelRepository entregavelRepository;

    public List<Entregavel> listarTodas() {
        return entregavelRepository.findAll();
    }

    public Optional<Entregavel> buscarPorId(Long id) {
        return entregavelRepository.findById(id);
    }

    public Entregavel salvar(Entregavel entregavel) {
        return entregavelRepository.save(entregavel);
    }

    public void deletar(Long id) {
        entregavelRepository.deleteById(id);
    }
}

package com.getinfo.contratos.service;

import com.getinfo.contratos.entity.Repactuacao;
import com.getinfo.contratos.repository.RepactuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepactuacaoService {

    @Autowired
    private RepactuacaoRepository repactuacaoRepository;

    public List<Repactuacao> listarTodas() {
        return repactuacaoRepository.findAll();
    }


    public Optional<Repactuacao> buscarPorId(Long id) {
        return repactuacaoRepository.findById(id);
    }

    public Repactuacao salvar(Repactuacao repactuacao) {
        return repactuacaoRepository.save(repactuacao);
    }

    public void deletar(Long id) {
        repactuacaoRepository.deleteById(id);
    }

}

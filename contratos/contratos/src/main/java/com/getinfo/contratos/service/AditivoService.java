package com.getinfo.contratos.service;

import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.repository.AditivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AditivoService {

    @Autowired
    private AditivoRepository aditivoRepository;

    public List<Aditivo> listarTodas() {
        return aditivoRepository.findAll();
    }

    public Optional<Aditivo> buscarPorId(Long id) {
        return aditivoRepository.findById(id);
    }

    public Aditivo salvar(Aditivo aditivo) {
        return aditivoRepository.save(aditivo);
    }
    public void deletar(Long id) {
        aditivoRepository.deleteById(id);
    }

}

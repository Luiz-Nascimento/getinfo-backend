package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.entity.Usuario;
import com.getinfo.contratos.mappers.UsuarioMapper;
import com.getinfo.contratos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    public List<UsuarioExibirDTO> usuarioExibirDTOS() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioExibirDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario: usuarios) {
            usuariosDTO.add(usuarioMapper.entityToExibirDTO(usuario));
        }
        return usuariosDTO;
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }


}




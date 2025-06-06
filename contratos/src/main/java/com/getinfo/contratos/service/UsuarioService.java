package com.getinfo.contratos.service;

import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.DTOs.UsuarioRegistroDTO;
import com.getinfo.contratos.entity.Usuario;
import com.getinfo.contratos.mappers.UsuarioMapper;
import com.getinfo.contratos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public Optional<UsuarioExibirDTO> buscarPorIdDTO(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioMapper.optionalEntityToOptionalDTO(usuarioOptional);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void registrarNovoUsuario(UsuarioRegistroDTO dto){
        if (!dto.senha().equals(dto.confirmarSenha())) {
            throw new IllegalArgumentException("As senhas não conferem!");
        }

        if (usuarioRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Nome de usuário já existente");
        }
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já existente");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
    }

    public boolean deletar(Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }


}




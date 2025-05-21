package com.getinfo.contratos.DTOs;

public record UsuarioRegistroDTO(
        String username,
        String email,
        String senha,
        String confirmarSenha
) {
}

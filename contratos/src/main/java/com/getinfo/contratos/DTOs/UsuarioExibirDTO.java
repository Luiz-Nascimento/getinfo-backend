package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.Roles;

import java.util.Set;

public record UsuarioExibirDTO(
        Long id,
        String username,
        String email,
        Set<Roles> roles
) {
}

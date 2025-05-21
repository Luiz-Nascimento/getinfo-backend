package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.Roles;

import java.util.Set;

public record UsuarioExibirDTO(
        String username,
        String email,
        Set<Roles> roles
) {
}

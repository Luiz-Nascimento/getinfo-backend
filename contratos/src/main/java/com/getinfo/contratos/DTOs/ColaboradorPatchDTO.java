package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.ColaboradorStatus;

public record ColaboradorPatchDTO(
        String email,
        String telefone,
        String cargo) {
}

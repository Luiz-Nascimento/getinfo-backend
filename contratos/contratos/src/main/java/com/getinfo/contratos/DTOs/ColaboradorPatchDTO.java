package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.ColaboradorStatus;

public record ColaboradorPatchDTO(
        ColaboradorStatus status,
        String email,
        String telefone,
        String cargo) {
}

package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.ColaboradorStatus;

public record ColaboradorPatchDTO(
        ColaboradorStatus status,
        String telefone,
        String cargo) {
}

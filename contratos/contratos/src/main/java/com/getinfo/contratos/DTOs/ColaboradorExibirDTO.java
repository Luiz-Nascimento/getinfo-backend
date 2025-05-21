package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.ColaboradorStatus;

public record ColaboradorExibirDTO(
        Long id,
        String nome,
        String sobrenome,
        ColaboradorStatus status,
        String cargo
)
{}

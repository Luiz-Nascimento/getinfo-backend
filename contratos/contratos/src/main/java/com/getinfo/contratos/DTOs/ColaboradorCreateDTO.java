package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.ColaboradorStatus;

public record ColaboradorCreateDTO(
        String nome,
        String sobrenome,
        ColaboradorStatus status,
        String cpf,
        String email,
        String telefone,
        String cargo

) {}

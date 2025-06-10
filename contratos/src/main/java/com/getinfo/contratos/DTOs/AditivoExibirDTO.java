package com.getinfo.contratos.DTOs;

import java.math.BigDecimal;

public record AditivoExibirDTO(
        Long id,
        Long diasAditivo,
        BigDecimal valorAditivo,
        String descricao
)
{}

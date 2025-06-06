package com.getinfo.contratos.DTOs;

import java.math.BigDecimal;

public record AditivoExibirDTO(
        Long idContrato,
        BigDecimal valorAditivo,
        Long diasAditivo,
        String descricao
) {
}

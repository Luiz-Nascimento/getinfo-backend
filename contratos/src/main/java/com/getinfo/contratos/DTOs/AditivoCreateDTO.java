package com.getinfo.contratos.DTOs;

import java.math.BigDecimal;

public record AditivoCreateDTO(
        Long idContrato,
        BigDecimal valorAditivo,
        Long diasAditivo,
        String descricao
) {
}

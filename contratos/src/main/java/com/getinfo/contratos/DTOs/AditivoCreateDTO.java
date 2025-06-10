package com.getinfo.contratos.DTOs;

import java.math.BigDecimal;

public record AditivoCreateDTO(
        Long diasAditivo,
        BigDecimal valorAditivo,
        String descricao
) {
}

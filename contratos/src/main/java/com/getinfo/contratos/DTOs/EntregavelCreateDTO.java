package com.getinfo.contratos.DTOs;

import java.time.LocalDate;

public record EntregavelCreateDTO(
        String descricao,
        String observacao,
        LocalDate dataFinal
) {
}

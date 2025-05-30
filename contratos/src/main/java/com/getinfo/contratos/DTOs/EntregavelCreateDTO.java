package com.getinfo.contratos.DTOs;

import java.time.LocalDate;

public record EntregavelCreateDTO(
        Long contratoId,
        String descricao,
        String observacao,
        LocalDate dataFinal
) {
}

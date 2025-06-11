package com.getinfo.contratos.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RepactuacaoCreateDTO(
        BigDecimal novoValorContrato,
        LocalDate dataRepactuacao,
        LocalDate novaDataFinal,
        String descricao,
        String motivoRepactuacao
) {
}

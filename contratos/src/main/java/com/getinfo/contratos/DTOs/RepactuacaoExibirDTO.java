package com.getinfo.contratos.DTOs;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RepactuacaoExibirDTO(
        Long id,
        Long idContrato,
        BigDecimal valorOriginalContrato,
        BigDecimal novoValorContrato,
        LocalDate dataRepactuacao,
        LocalDate novaDataFinal,
        String descricao,
        String motivoRepactuacao
) {
}

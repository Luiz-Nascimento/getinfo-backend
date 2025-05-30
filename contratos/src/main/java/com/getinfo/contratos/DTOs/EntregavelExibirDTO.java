package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.StatusEntregavel;

import java.time.LocalDate;

public record EntregavelExibirDTO (
        StatusEntregavel status,
        String descricao,
        String observacao,
        String justificativaCancelamento,
        LocalDate dataCancelamento,
        LocalDate dataFinal,
        LocalDate dataEntrega
)
{}
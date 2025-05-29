package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoExibirDTO(
        Long id,
        String nomeFantasia,
        String cnpj,
        StatusContrato statusContrato,
        BigDecimal valor,
        String descricao,
        TipoContrato tipo,
        LocalDate dataInicio,
        LocalDate dataFim,
        String nomeResponsavel
) {
}

package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.entity.Empresa;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;

import java.math.BigDecimal;

public record ContratoExibirDTO(
        Long id,
        String nomeFantasia,
        StatusContrato statusContrato,
        BigDecimal valor,
        String descricao,
        TipoContrato tipo
) {
}

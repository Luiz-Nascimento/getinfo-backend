package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record ContratoExibirDTO(
        Long id,
        StatusContrato status,
        BigDecimal valor,
        TipoContrato tipo,
        LocalDate dataInicio,
        LocalDate dataFim,
        String nomeFantasiaEmpresa,
        Set<String> nomesColaboradores
) {
    public void setNomeFantasiaEmpresa(Object o) {
    }
}

package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.TipoContrato;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoCreateDTO(
    String cnpj,
    BigDecimal valor,
    String descricao,
    TipoContrato tipo,
    LocalDate dataInicio,
    LocalDate dataFim
    )
{


}

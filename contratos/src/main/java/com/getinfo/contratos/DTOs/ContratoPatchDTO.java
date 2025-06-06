package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;

public record ContratoPatchDTO(
        String descricao,
        TipoContrato tipo,
        StatusContrato status,
        String nomeResponsavel
) {
}

package com.getinfo.contratos.DTOs;


import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ContratoCreateDTO {

    @NotBlank
    private Long empresaId;

    @NotNull
    private List<Long> idsColaboradores;

    @NotBlank
    private String descricao;

    @NotNull
    private TipoContrato tipo;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;
}

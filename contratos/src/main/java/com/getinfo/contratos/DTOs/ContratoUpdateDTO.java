package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.StatusContrato;
import com.getinfo.contratos.enums.TipoContrato;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ContratoUpdateDTO {

    // ou pode ficar fora e vir pela URL
    @NotNull
    private Long id;

    @NotNull
    private Long empresaId;

    @NotNull
    @Size(min = 1)
    private List<Long> idsColaboradores;

    @NotNull
    private StatusContrato status;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal valor;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    private TipoContrato tipo;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;
}

package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.TipoContrato;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContratoCreateDTO(

        @NotBlank(message = "O CNPJ é obrigatório")
        @CNPJ(message = "CNPJ inválido")
        String cnpj,

        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero")
        BigDecimal valor,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(min = 5, max = 255, message = "A descrição deve ter entre 5 e 255 caracteres")
        String descricao,

        @NotNull(message = "O tipo de contrato é obrigatório")
        TipoContrato tipo,

        @NotNull(message = "A data de início é obrigatória")
        @PastOrPresent(message = "A data de início não pode ser no futuro")
        LocalDate dataInicio,

        @NotNull(message = "A data de fim é obrigatória")
        @Future(message = "A data de fim deve ser no futuro")
        LocalDate dataFim

) {}
package com.getinfo.contratos.DTOs;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import com.getinfo.contratos.enums.ColaboradorStatus;

public record ColaboradorCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Sobrenome é obrigatório")
        String sobrenome,

        @NotNull(message = "Status é obrigatório")
        ColaboradorStatus status,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "Telefone deve seguir o padrão (99) 99999-9999")
        String telefone,

        @NotBlank(message = "Cargo é obrigatório")
        String cargo
) {}
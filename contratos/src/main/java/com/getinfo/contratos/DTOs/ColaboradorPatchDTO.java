package com.getinfo.contratos.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ColaboradorPatchDTO(

        @Size(min = 2, max = 50, message = "O campo nome deve ter entre 2 e 50 caracteres")
        String nome,
        @Size(min = 2, max = 50, message = "O campo sobrenome deve ter entre 2 e 50 caracteres")
        String sobrenome,
        @Email(message = "E-mail em formato inválido")
        String email,

        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone em formato inválido. Exemplo: (79) 99999-8888")
        String telefone,

        @Size(min = 2, max = 50, message = "O cargo deve ter entre 2 e 50 caracteres")
        String cargo

) {}
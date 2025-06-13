package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.Estado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record EmpresaPatchDTO(
        @Size(min = 2, max = 80, message = "O nome fantasia deve ter entre 2 e 80 caracteres")
        String nomeFantasia,
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String cep,
        @Size(min = 2, max = 100, message = "O campo logradouro deve ter entre 2 e 100 caracteres")
        String logradouro,
        @Size(min = 2, max = 50, message = "O campo bairro deve ter entre 2 e 50 caracteres")
        String bairro,
        String numero,
        Estado estado,
        String cidade,
        String complemento,
        @Email(message = "E-mail em formato inválido")
        String email,
        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone em formato inválido. Exemplo: (79) 99999-8888")
        String telefone,
        @Size(min = 2, max = 100, message = "O nome do responsável deve ter entre 2 e 100 caracteres")
        String nomeResponsavel,
        @Email(message = "E-mail do responsável em formato inválido")
        String emailResponsavel,
        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone do responsável em formato inválido. Exemplo: (79) 99999-8888")
        String telefoneResponsavel,
        @CPF(message = "CPF do responsável inválido")
        String cpfResponsavel,
        Boolean ativo


) {
}

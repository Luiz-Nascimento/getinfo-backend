package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.Estado;
import com.getinfo.contratos.enums.TipoEmpresa;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record EmpresaCreateDTO(
        @NotBlank(message = "O CNPJ é obrigatório")
        @CNPJ(message = "CNPJ inválido")
        String cnpj,
        @NotBlank(message = "A razão social é obrigatória")
        @Size(min = 2, max = 60, message = "A razão social deve ter entre 2 e 60 caracteres")
        String razaoSocial,
        @NotBlank(message = "O campo nome fantasia é obrigatório")
        @Size(min = 2, max = 80, message = "O nome fantasia deve ter entre 2 e 80 caracteres")
        String nomeFantasia,
        @NotNull(message = "O campo tipo não pode ser nulo")
        TipoEmpresa tipo,
        @NotBlank(message = "O Campo CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        String cep,
        @NotBlank(message = "O campo logradouro é obrigatório")
        @Size(min = 2, max = 100, message = "O campo logradouro deve ter entre 2 e 100 caracteres")
        String logradouro,
        @NotBlank(message = "O campo bairro é obrigatório")
        @Size(min = 2, max = 50, message = "O campo bairro deve ter entre 2 e 50 caracteres")
        String bairro,
        @NotBlank(message = "O campo numero é obrigatório")
        String numero,
        @NotNull(message = "O campo estado não pode ser nulo")
        Estado estado,
        @NotBlank(message = "O campo cidade é obrigatório")
        String cidade,
        String complemento,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail em formato inválido")
        String email,
        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone em formato inválido. Exemplo: (79) 99999-8888")
        String telefone,
        @NotBlank(message = "O campo nome do responsável não pode ser nulo")
        @Size(min = 2, max = 100, message = "O nome do responsável deve ter entre 2 e 100 caracteres")
        String nomeResponsavel,
        @NotBlank(message = "O email do responsável é obrigatório")
        @Email(message = "E-mail do responsável em formato inválido")
        String emailResponsavel,
        @NotBlank(message = "O telefone do responsável é obrigatório")
        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone do responsável em formato inválido. Exemplo: (79) 99999-8888")
        String telefoneResponsavel,
        @NotBlank(message = "O campo CPF do responsável é obrigatório")
        @CPF(message = "CPF do responsável inválido")
        String cpfResponsavel
) {
}

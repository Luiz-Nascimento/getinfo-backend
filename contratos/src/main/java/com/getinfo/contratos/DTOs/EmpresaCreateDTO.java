package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.Estado;
import com.getinfo.contratos.enums.TipoEmpresa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "DTO para criação de uma nova empresa no sistema. Contém todos os dados necessários para o cadastro inicial.")
public record EmpresaCreateDTO(

        @NotBlank(message = "O CNPJ é obrigatório")
        @CNPJ(message = "CNPJ inválido")
        @Schema(description = "Número do Cadastro Nacional de Pessoas Jurídicas (CNPJ) da empresa. Formato: 14 dígitos numéricos.",
                example = "12345678000190",
                requiredMode = Schema.RequiredMode.REQUIRED,
                pattern = "\\d{14}")
        String cnpj,

        @NotBlank(message = "A razão social é obrigatória")
        @Size(min = 2, max = 60, message = "A razão social deve ter entre 2 e 60 caracteres")
        @Schema(description = "Razão social completa da empresa.",
                example = "Empresa de Tecnologia XYZ Ltda.",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 2, maxLength = 60)
        String razaoSocial,

        @NotBlank(message = "O campo nome fantasia é obrigatório")
        @Size(min = 2, max = 80, message = "O nome fantasia deve ter entre 2 e 80 caracteres")
        @Schema(description = "Nome comercial ou fantasia da empresa.",
                example = "Tech Solutions",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 2, maxLength = 80)
        String nomeFantasia,

        @NotNull(message = "O campo tipo não pode ser nulo")
        @Schema(description = "Tipo de empresa (ex: 'PUBLICA', 'PRIVADA').",
                example = "PRIVADA",
                requiredMode = Schema.RequiredMode.REQUIRED)
        TipoEmpresa tipo,

        @NotBlank(message = "O Campo CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "CEP inválido")
        @Schema(description = "Código de Endereçamento Postal (CEP) da empresa. Formato: 'XXXXX-XXX' ou 'XXXXXXXX'.",
                example = "49000-000",
                requiredMode = Schema.RequiredMode.REQUIRED,
                pattern = "\\d{5}-?\\d{3}")
        String cep,

        @NotBlank(message = "O campo logradouro é obrigatório")
        @Size(min = 2, max = 100, message = "O campo logradouro deve ter entre 2 e 100 caracteres")
        @Schema(description = "Nome do logradouro (rua, avenida, praça) do endereço da empresa.",
                example = "Avenida Barão de Maruim",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 2, maxLength = 100)
        String logradouro,

        @NotBlank(message = "O campo bairro é obrigatório")
        @Size(min = 2, max = 50, message = "O campo bairro deve ter entre 2 e 50 caracteres")
        @Schema(description = "Nome do bairro do endereço da empresa.",
                example = "Centro",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 2, maxLength = 50)
        String bairro,

        @NotBlank(message = "O campo numero é obrigatório")
        @Schema(description = "Número do edifício ou residência no logradouro.",
                example = "500",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String numero,

        @NotNull(message = "O campo estado não pode ser nulo")
        @Schema(description = "Sigla do estado brasileiro onde a empresa está localizada.",
                example = "SE",
                requiredMode = Schema.RequiredMode.REQUIRED)
        Estado estado,

        @NotBlank(message = "O campo cidade é obrigatório")
        @Schema(description = "Nome da cidade onde a empresa está localizada.",
                example = "Aracaju",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String cidade,

        @Schema(description = "Informações adicionais para o endereço (ex: sala, andar, bloco).",
                example = "Sala 101, Edifício Comercial",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String complemento,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail em formato inválido")
        @Schema(description = "Endereço de e-mail principal da empresa.",
                example = "contato@empresa.com.br",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "email")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone em formato inválido. Exemplo: (79) 99999-8888")
        @Schema(description = "Telefone de contato principal da empresa. Formato: (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.",
                example = "(79) 3218-1234",
                requiredMode = Schema.RequiredMode.REQUIRED,
                pattern = "\\(\\d{2}\\) \\d{4,5}-\\d{4}")
        String telefone,

        @NotBlank(message = "O campo nome do responsável não pode ser nulo")
        @Size(min = 2, max = 100, message = "O nome do responsável deve ter entre 2 e 100 caracteres")
        @Schema(description = "Nome completo do responsável legal pela empresa.",
                example = "João da Silva",
                requiredMode = Schema.RequiredMode.REQUIRED,
                minLength = 2, maxLength = 100)
        String nomeResponsavel,

        @NotBlank(message = "O email do responsável é obrigatório")
        @Email(message = "E-mail do responsável em formato inválido")
        @Schema(description = "Endereço de e-mail do responsável legal pela empresa.",
                example = "joao.silva@empresa.com.br",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "email")
        String emailResponsavel,

        @NotBlank(message = "O telefone do responsável é obrigatório")
        @Pattern(
                regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
                message = "Telefone do responsável em formato inválido. Exemplo: (79) 99999-8888")
        @Schema(description = "Telefone de contato do responsável legal. Formato: (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.",
                example = "(79) 99876-5432",
                requiredMode = Schema.RequiredMode.REQUIRED,
                pattern = "\\(\\d{2}\\) \\d{4,5}-\\d{4}")
        String telefoneResponsavel,

        @NotBlank(message = "O campo CPF do responsável é obrigatório")
        @CPF(message = "CPF do responsável inválido")
        @Schema(description = "Número do Cadastro de Pessoa Física (CPF) do responsável legal. Formato: 11 dígitos numéricos.",
                example = "12345678900",
                requiredMode = Schema.RequiredMode.REQUIRED,
                pattern = "\\d{11}")
        String cpfResponsavel
) {
}
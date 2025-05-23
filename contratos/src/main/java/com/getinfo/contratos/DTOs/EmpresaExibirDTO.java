package com.getinfo.contratos.DTOs;

import com.getinfo.contratos.enums.Estado;
import com.getinfo.contratos.enums.TipoEmpresa;

public record EmpresaExibirDTO(
        Long id,
        String razaoSocial,
        String cnpj,
        String nomeFantasia,
        TipoEmpresa tipo,
        String cep,
        String logradouro,
        String bairro,
        String numero,
        Estado estado,
        String cidade,
        String complemento,
        String email,
        String telefone,
        String nomeResponsavel,
        String emailResponsavel,
        String telefoneResponsavel,
        String cpfResponsavel,
        Boolean ativo


)
{}

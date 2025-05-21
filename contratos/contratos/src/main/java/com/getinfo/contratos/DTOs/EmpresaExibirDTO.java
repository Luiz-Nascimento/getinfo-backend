package com.getinfo.contratos.DTOs;

public record EmpresaExibirDTO(
        Long id,
        String razaoSocial,
        String cnpj,
        String nomeFantasia,
        String emailResponsavel,
        String nomeResponsavel)
{}

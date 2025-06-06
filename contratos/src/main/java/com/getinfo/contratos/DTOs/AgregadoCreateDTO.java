package com.getinfo.contratos.DTOs;

public record AgregadoCreateDTO(
        Long idColaborador,
        Long idContrato,
        String cargoContrato
) {
}

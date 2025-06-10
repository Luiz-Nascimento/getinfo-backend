package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Entregavel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntregavelMapper {

    Entregavel fromDto(EntregavelCreateDTO dto);

    @Mapping(source = "contrato.id", target = "idContrato")
    EntregavelExibirDTO toDto(Entregavel entregavel);
}

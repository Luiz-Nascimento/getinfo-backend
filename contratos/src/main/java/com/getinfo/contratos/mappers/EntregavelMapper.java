package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.EntregavelCreateDTO;
import com.getinfo.contratos.DTOs.EntregavelExibirDTO;
import com.getinfo.contratos.entity.Entregavel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntregavelMapper {

    Entregavel fromDto(EntregavelCreateDTO dto);

    EntregavelExibirDTO toDto(Entregavel entregavel);
}

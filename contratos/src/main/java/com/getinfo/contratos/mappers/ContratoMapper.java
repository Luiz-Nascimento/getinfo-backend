package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.entity.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    @Mapping(target = "empresa", ignore = true)
    Contrato createDtoToEntity(ContratoCreateDTO contratoCreateDTO);
}

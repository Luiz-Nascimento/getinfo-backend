package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.AditivoCreateDTO;
import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import com.getinfo.contratos.entity.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AditivoMapper {


    @Mapping(target = "contrato", ignore = true)
    Aditivo createDtoToEntity(AditivoCreateDTO aditivoCreateDTO);

    @Mapping(source = "contrato.id", target = "idContrato")
    AditivoExibirDTO entityToDto(Aditivo aditivo);
}

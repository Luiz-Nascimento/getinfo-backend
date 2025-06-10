package com.getinfo.contratos.mappers;


import com.getinfo.contratos.DTOs.AditivoCreateDTO;
import com.getinfo.contratos.DTOs.AditivoExibirDTO;
import com.getinfo.contratos.entity.Aditivo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AditivoMapper {

    @Mapping(source = "contrato.id", target = "idContrato")
    AditivoExibirDTO toExibirDTO(Aditivo aditivo);

    Aditivo toEntity(AditivoCreateDTO aditivoCreateDTO);
}

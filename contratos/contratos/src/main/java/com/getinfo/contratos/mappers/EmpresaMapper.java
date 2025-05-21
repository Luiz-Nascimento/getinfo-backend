package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.entity.Empresa;
import org.mapstruct.Mapper;

import java.util.Optional;


@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    Empresa createDTOtoEntity(EmpresaCreateDTO empresaCreateDTO);
    EmpresaExibirDTO entityToExibirDTO(Empresa empresa);
    default
    Optional<EmpresaExibirDTO> optionalEntityToOptionalExibirDTO(Optional<Empresa> empresa) {
        return empresa.map(this::entityToExibirDTO);
    }
}

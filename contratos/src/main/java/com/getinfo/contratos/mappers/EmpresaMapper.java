package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.EmpresaCreateDTO;
import com.getinfo.contratos.DTOs.EmpresaExibirDTO;
import com.getinfo.contratos.DTOs.EmpresaPatchDTO;
import com.getinfo.contratos.entity.Empresa;
import org.mapstruct.*;

import java.util.Optional;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmpresaMapper {

    Empresa createDTOtoEntity(EmpresaCreateDTO empresaCreateDTO);
    EmpresaExibirDTO entityToExibirDTO(Empresa empresa);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchEmpresaFromDto(EmpresaPatchDTO dto, @MappingTarget
                             Empresa entity);
    default
    Optional<EmpresaExibirDTO> optionalEntityToOptionalExibirDTO(Optional<Empresa> empresa) {
        return empresa.map(this::entityToExibirDTO);
    }
}

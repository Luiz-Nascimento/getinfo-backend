package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.ContratoPatchDTO;
import com.getinfo.contratos.entity.Contrato;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    @Mapping(target = "empresa", ignore = true)
    Contrato createDtoToEntity(ContratoCreateDTO contratoCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchContratoFromDto(ContratoPatchDTO dto, @MappingTarget Contrato entity);


    ContratoExibirDTO entityToDTO(Contrato contrato);
}

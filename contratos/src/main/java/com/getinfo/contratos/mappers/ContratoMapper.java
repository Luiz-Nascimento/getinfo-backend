package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.ContratoPatchDTO;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.entity.Empresa;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    /**
     * Converte um ContratoCreateDTO em uma entidade Contrato.
     * A entidade Empresa, já buscada no serviço, é passada como parâmetro.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true) // O status provavelmente tem um valor padrão ou é definido por regra de negócio, e não vem no DTO de criação.
    @Mapping(target = "empresa", source = "empresaEncontrada")
    @Mapping(target = "nomeResponsavel", source = "contratoCreateDTO.nomeResponsavel")
    @Mapping(target = "tipo", source = "contratoCreateDTO.tipo")
    Contrato createDtoToEntity(ContratoCreateDTO contratoCreateDTO, Empresa empresaEncontrada);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchContratoFromDto(ContratoPatchDTO dto, @MappingTarget Contrato entity);


    /**
     * Converte uma entidade Contrato para um ContratoExibirDTO.
     * Mapeia os campos aninhados da Empresa para os campos do DTO.
     */
    @Mapping(source = "empresa.nomeFantasia", target = "nomeFantasia")
    @Mapping(source = "empresa.cnpj", target = "cnpj")
    @Mapping(source = "status", target = "statusContrato") // Mapeia status para statusContrato
    @Mapping(source = "nomeResponsavel", target = "nomeResponsavel")
    @Mapping(source = "tipo", target = "tipo")

    ContratoExibirDTO entityToDTO(Contrato contrato);
}

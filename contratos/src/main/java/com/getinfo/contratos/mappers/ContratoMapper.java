package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.ContratoCreateDTO;
import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.DTOs.ContratoUpdateDTO;
import com.getinfo.contratos.entity.Contrato;
import com.getinfo.contratos.entity.Colaborador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContratoMapper {

    // Mapear DTO de criação para entidade
    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "anexo", ignore = true)
    Contrato createDTOtoEntity(ContratoCreateDTO contratoCreateDTO);

    // Mapear DTO de atualização para entidade
    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "anexo", ignore = true)
    Contrato updateDTOtoEntity(ContratoUpdateDTO contratoUpdateDTO);

    // Mapear entidade para DTO de exibição
    @Mapping(source = "empresa.nomeFantasia", target = "nomeFantasiaEmpresa")
    @Mapping(target = "nomesColaboradores", expression = "java(mapearNomesColaboradores(contrato))")
    ContratoExibirDTO entityToExibirDTO(Contrato contrato);

    List<ContratoExibirDTO> entityListToExibirDTOList(List<Contrato> contratos);

    default Set<String> mapearNomesColaboradores(Contrato contrato) {
        if (contrato.getColaboradores() == null) {
            return Collections.emptySet();
        }

        return contrato.getColaboradores().stream()
                .map(Colaborador::getNome)
                .collect(Collectors.toSet());
    }

    default Optional<ContratoExibirDTO> optionalEntityToOptionalExibirDTO(Optional<Contrato> contrato) {
        return contrato.map(this::entityToExibirDTO);
    }
}

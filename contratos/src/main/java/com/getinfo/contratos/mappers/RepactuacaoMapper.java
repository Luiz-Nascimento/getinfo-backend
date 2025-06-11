package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.RepactuacaoCreateDTO;
import com.getinfo.contratos.DTOs.RepactuacaoExibirDTO;
import com.getinfo.contratos.entity.Repactuacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepactuacaoMapper {

    @Mapping(source = "contrato.id", target = "idContrato")
    @Mapping(source = "contrato.valor", target = "valorOriginalContrato")
    RepactuacaoExibirDTO toDto(Repactuacao repactuacao);

    Repactuacao toEntity(RepactuacaoCreateDTO repactuacaoCreateDTO);
}

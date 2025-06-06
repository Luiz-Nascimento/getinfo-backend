package com.getinfo.contratos.mappers;

import com.getinfo.contratos.DTOs.AgregadoExibirDTO;
import com.getinfo.contratos.entity.Agregado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AgregadoMapper {

    // ... outros métodos de mapeamento que você já tem ...

    /**
     * Converte a entidade Agregado para o DTO de exibição.
     * O MapStruct mapeia 'cargoContrato' automaticamente.
     * Para os outros campos, indicamos o caminho a partir da entidade Colaborador.
     */
    @Mapping(source = "colaborador.nome", target = "nome")
    @Mapping(source = "colaborador.sobrenome", target = "sobrenome")
    @Mapping(source = "colaborador.email", target = "email")
    @Mapping(source = "colaborador.telefone", target = "telefone")
    AgregadoExibirDTO toExibirDTO(Agregado agregado);

    // Método para converter listas (muito útil!)
    List<AgregadoExibirDTO> toExibirDTOList(List<Agregado> agregados);

    // O NOVO método que retorna um Set
    // É uma boa prática que a entrada também seja um Set, mas não é obrigatório.
    Set<AgregadoExibirDTO> toExibirDTOSet(Set<Agregado> agregados);
}

package com.getinfo.contratos.mappers;


import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioExibirDTO entityToExibirDTO(Usuario usuario);

    default Optional<UsuarioExibirDTO> optionalEntityToOptionalDTO(Optional<Usuario> usuario){
        return usuario.map(this::entityToExibirDTO);
    }
}

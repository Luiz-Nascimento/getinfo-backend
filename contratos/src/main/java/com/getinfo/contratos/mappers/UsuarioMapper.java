package com.getinfo.contratos.mappers;


import com.getinfo.contratos.DTOs.UsuarioExibirDTO;
import com.getinfo.contratos.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioExibirDTO entityToExibirDTO(Usuario usuario);
}

package com.getinfo.contratos.config;

import com.getinfo.contratos.DTOs.ContratoExibirDTO;
import com.getinfo.contratos.entity.Contrato;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Contrato.class, ContratoExibirDTO.class)
                .addMappings(mapper -> mapper.skip(ContratoExibirDTO::setNomeFantasiaEmpresa));

        return modelMapper;
    }
}

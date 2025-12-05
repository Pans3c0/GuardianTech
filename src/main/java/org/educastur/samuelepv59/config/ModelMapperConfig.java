package org.educastur.samuelepv59.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.educastur.samuelepv59.dto.DocenteDTO;
import org.educastur.samuelepv59.model.Docente;

import org.modelmapper.ModelMapper;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();
        // No hace falta configurar EmpleadoDTO porque los nombres de los atributos
        // coinciden.

        // Mapeo profundo para EmpleadoOficinaDTO
        mapper.createTypeMap(Docente.class, DocenteDTO.class)
                .addMapping(src -> src.getNombre(),
                        DocenteDTO::setNomDocente)
                .addMapping(src -> src.getId(),
                        DocenteDTO::setNumemp)
                .addMapping(src -> src.getEmail(),
                        DocenteDTO::setEmail)
                .addMapping(src -> src.getDepartamento().getNombre(), DocenteDTO::setNomDepartamento);

        return mapper;
    }
}
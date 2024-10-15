package com.example.proyectogym.mappers;

import com.example.proyectogym.dto.MensajeAbonoSocioDTO;
import com.example.proyectogym.modelos.AbonoSocio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapperAbonoSocio {

    MapperAbonoSocio INSTANCE = Mappers.getMapper(MapperAbonoSocio.class);

    @Mapping(target = "mensaje", source = "mensaje")
    @Mapping(target = "nuevoAbonoSocio", source = "nuevoAbonoSocio")
    MensajeAbonoSocioDTO toDto(String mensaje, AbonoSocio nuevoAbonoSocio);
}

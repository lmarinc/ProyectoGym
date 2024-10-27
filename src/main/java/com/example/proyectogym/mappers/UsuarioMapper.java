package com.example.proyectogym.mappers;

import com.example.proyectogym.dto.UsuarioDTO;
import com.example.proyectogym.modelos.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario entity);

    List<Usuario> toEntity(List<UsuarioDTO> dtos);

    List<UsuarioDTO> toDTO(List<Usuario> entities);



}

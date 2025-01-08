package com.example.proyectogym.dto;

import com.example.proyectogym.enumerados.Rol;
import lombok.Data;



@Data
public class UsuarioDTO {

    private Integer id;

    private String username;

    private String password;

    private Rol rol;
}

package com.example.proyectogym.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioDTO {

    private String nombre;
    private String apellidos;
    private String correo;
}

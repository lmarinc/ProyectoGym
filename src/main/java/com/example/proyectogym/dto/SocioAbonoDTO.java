package com.example.proyectogym.dto;

import com.example.proyectogym.modelos.Socio;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioAbonoDTO {
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correo;
    private Integer abonoId;
    private Double precio;
    private String fechaInicio;
}
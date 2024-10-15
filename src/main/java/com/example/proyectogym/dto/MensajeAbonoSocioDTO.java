package com.example.proyectogym.dto;

import com.example.proyectogym.modelos.AbonoSocio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MensajeAbonoSocioDTO {
    private String mensaje;
    private AbonoSocio nuevoAbonoSocio;

}

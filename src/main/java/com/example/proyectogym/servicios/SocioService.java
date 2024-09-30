package com.example.proyectogym.servicios;

import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocioService {

    private SocioRepositorio socioRepositorio;

    public void eliminar(Integer id) {
        socioRepositorio.deleteById(id);
    }

    public void eliminar(Socio socio) {
        socioRepositorio.delete(socio);
    }



}

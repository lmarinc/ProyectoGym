package com.example.proyectogym;

import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProyectoGymApplicationTests {

    @Autowired
    private SocioRepositorio socioRepositorio;
    @Test
    void testFindAllSocios() {
        List<Socio> socios = socioRepositorio.findAll();
        for(Socio socio : socios){
            System.out.println(socio);
        }
    }

}

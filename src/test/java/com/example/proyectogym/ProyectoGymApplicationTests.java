package com.example.proyectogym;

import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import com.example.proyectogym.servicios.AbonoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProyectoGymApplicationTests {

//    @Autowired
//    private SocioRepositorio socioRepositorio;
//    @Test
//    void testFindAllSocios() {
//        List<Socio> socios = socioRepositorio.findAll();
//        for(Socio socio : socios){
//            System.out.println(socio);
//        }
//    }
    @Autowired
    private AbonoService abonoService;
    @Test
    void testFindAllAbonos() {
        System.out.println(abonoService.getAbonoPorTipo(TipoAbono.valueOf("MENSUAL")));
    }


}

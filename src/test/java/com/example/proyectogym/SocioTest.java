package com.example.proyectogym;

import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.servicios.AbonoSocioService;
import com.example.proyectogym.servicios.SocioService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class SocioTest {

    @Autowired
    private SocioService socioService;

    @Test
    void testFindAllSocios() {
        List<Socio> socios = socioService.getAll();
        for (Socio socio : socios) {
            System.out.println(socio);
        }
    }

    @Test
    void testGuardarSocio() {
        Socio socio = new Socio();
        socio.setNombre("Juan");
        socio.setApellidos("Pérez");
        socio.setDni("12345678");
        socio.setTelefono("12345678");
        socio.setCorreo("juan@juan.es");
        socio.setEsActivo(true);
        socioService.guardar(socio);
    }

    @Test
    @Transactional
    @Commit
    void testEliminarSocio() {
        socioService.eliminar(21);
    }

    @Test
    void testDesactivarSocio() {
        Socio socio = socioService.getSocioPorId(1);
        socio.setEsActivo(false);
        socioService.guardar(socio);
    }
}

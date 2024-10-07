package com.example.proyectogym;

import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.servicios.AbonoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AbonoTest {
    @Autowired
    private AbonoService abonoService;

    @Test
    void testFindAllAbonos() {
        List<Abono> abonos = abonoService.getAll();
        for (Abono abono : abonos) {
            System.out.println(abono.getNombre());
        }
    }

    @Test
    void testCrearAbono() {
        Abono abono = new Abono();
        abono.setNombre("Abono 1");
        abono.setDescripcion("Abono Prueba");
        abono.setTipoAbono(TipoAbono.MENSUAL);
        abono.setDuracion(30);
        abono.setPrecio(20.0);
        abonoService.guardar(abono);
    }
//    @Test
//    void testEliminarAbono() {
//        abonoService.eliminar(6);
//    }
    @Test
    void testEditarAbono() {
        Abono abono = abonoService.getAbonoPorId(2);
        abono.setNombre("Abono Trimestral");
        abono.setDescripcion("Abono Trimestral");
        abono.setTipoAbono(TipoAbono.TRIMESTRAL);
        abono.setDuracion(60);
        abono.setPrecio(140.0);
        abonoService.guardar(abono);
    }
}

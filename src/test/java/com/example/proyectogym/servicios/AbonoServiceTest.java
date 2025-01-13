package com.example.proyectogym.servicios;


import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class AbonoServiceTest {

    @Autowired
    private AbonoService abonoService;

    @Autowired
    private AbonoRepositorio abonoRepositorio;

    @BeforeEach
    public void inicializarDatos(){
        Abono abono1 = new Abono();
        abono1.setNombre("Abono Mensual");
        abono1.setPrecio(50.0);
        abono1.setTipoAbono(TipoAbono.MENSUAL);
        abonoRepositorio.save(abono1);

        Abono abono2 = new Abono();
        abono2.setNombre("Abono Trimestral");
        abono2.setPrecio(120.0);
        abono2.setTipoAbono(TipoAbono.TRIMESTRAL);
        abonoRepositorio.save(abono2);

        Abono abono3 = new Abono();
        abono3.setNombre("Abono Anual");
        abono3.setPrecio(400.0);
        abono3.setTipoAbono(TipoAbono.ANUAL);
        abonoRepositorio.save(abono3);

        Abono abono4 = new Abono();
        abono4.setNombre("Abono Semestral");
        abono4.setPrecio(200.0);
        abono4.setTipoAbono(TipoAbono.SEMESTRAL);
        abonoRepositorio.save(abono4);

    }

    @Test
    public void testGetAllAbono() {
        //GIVEN

        //WHEN
        List<Abono> abonos = abonoService.getAll();

        //THEN
        assertEquals(4, abonos.size());
    }

    @Test
    public void testFindAbonoById() {
        //GIVEN
        Integer id = 1;

        //WHEN
        Abono abono = abonoService.getAbonoPorId(id);

        //THEN
        assertEquals("Abono Mensual", abono.getNombre());
    }

    @Test
    public void testFindAbonoIdNegativo() {
        //GIVEN
        Integer id = 10;

        //WHEN
        Abono abono = abonoService.getAbonoPorId(id);

        //THEN
        Exception exception = assertThrows(Exception.class, () -> abonoService.getAbonoPorId(id));
        assertEquals("No existe ningun abono con ese id",exception.getMessage());
    }

    @Test
    public void testGuardarAbono() {
        //GIVEN
        Abono abono = new Abono();
        abono.setNombre("Abono Semestral Especial");
        abono.setPrecio(150.0);
        abono.setTipoAbono(TipoAbono.SEMESTRAL);

        //WHEN
        Abono abonoGuardado = abonoService.guardar(abono);

        //THEN
        assertNotNull(abonoGuardado);
        assertNotNull(abonoGuardado.getId());
    }
}

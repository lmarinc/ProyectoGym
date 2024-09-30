package com.example.proyectogym;

import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.servicios.AbonoService;
import com.example.proyectogym.servicios.AbonoSocioService;
import com.example.proyectogym.servicios.SocioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AbonoSocioTest {

    @Autowired
    private AbonoSocioService abonoSocioService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private AbonoService abonoService;

    @Test
    void testCrearAbonoSocio() {
        // Crear instancias de Socio y Abono
        Socio socio = new Socio();
        socio.setNombre("Juan");
        socio.setApellidos("Pérez");
        socio.setDni("12345678A");
        socio.setTelefono("123456789");
        socio.setCorreo("juan.perez@example.com");
        socio.setEsActivo(true);
        socio = socioService.save(socio);

        Abono abono = new Abono();
        abono.setNombre("Mensual");
        abono.setDescripcion("Abono mensual");
        abono.setTipoAbono(TipoAbono.MENSUAL);
        abono.setDuracion(30);
        abono.setPrecio(50.0);
        abono = abonoService.save(abono);

        // Crear instancia de AbonoSocio
        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);
        abonoSocio.setAbono(abono);
        abonoSocio.setFechaInicio(LocalDate.now());
        abonoSocio.setFechaFin(LocalDate.now().plusMonths(1));
        abonoSocio.setPrecio(50.0);

        // Guardar AbonoSocio
        AbonoSocio savedAbonoSocio = abonoSocioService.save(abonoSocio);

        // Verificar que la instancia fue guardada correctamente
        assertNotNull(savedAbonoSocio);
        assertNotNull(savedAbonoSocio.getId());
    }
}
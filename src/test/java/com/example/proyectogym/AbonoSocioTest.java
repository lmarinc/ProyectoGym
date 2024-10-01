package com.example.proyectogym;

import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.servicios.AbonoService;
import com.example.proyectogym.servicios.AbonoSocioService;
import com.example.proyectogym.servicios.SocioService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
    @Transactional
    @Commit
    void testCrearAbonoSocio() {
        // Crear instancias de Socio
        Socio socio = new Socio();
        socio.setNombre("Felipe");
        socio.setApellidos("Marin");
        socio.setDni("98746541");
        socio.setTelefono("999888777");
        socio.setCorreo("felipe.marin@example.com");
        socio.setEsActivo(true);
        socio = socioService.guardar(socio);

        // Buscar el abono existente por ID o algún otro campo (por ejemplo, nombre)
        Abono abono = abonoService.getAbonoPorNombre(TipoAbono.MENSUAL);

        if (abono == null) {
            throw new IllegalStateException("No se encontró el abono con nombre 'Mensual'.");
        }

        // Asegurarse de que el abono esté gestionado
//        abono = abonoService.merge(abono); // Si está "detached", se vuelve a conectar con el EntityManager.

        // Crear instancia de AbonoSocio
        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);
        abonoSocio.setAbono(abono);
        abonoSocio.setFechaInicio(LocalDate.now());
        abonoSocio.setFechaFin(LocalDate.now().plusMonths(1));
        abonoSocio.setPrecio(50.0);

        // Guardar AbonoSocio
        AbonoSocio savedAbonoSocio = abonoSocioService.guardar(abonoSocio);

        // Verificar que la instancia fue guardada correctamente
        assertNotNull(savedAbonoSocio);
        assertNotNull(savedAbonoSocio.getId());
    }

}
package com.example.proyectogym.servicios;

import com.example.proyectogym.enumerados.TipoAbono;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoRepositorio;
import com.example.proyectogym.repositorios.AbonoSocioRepositorio;
import com.example.proyectogym.repositorios.AsistenciaRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class EntregaTest {

    @Autowired
    private AbonoService abonoService;
    @Autowired
    private AbonoRepositorio abonoRepositorio;
    @Autowired
    private AsistenciaService asistenciaService;
    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;
    @Autowired
    private AbonoSocioService abonoSocioService;
    @Autowired
    private AbonoSocioRepositorio abonoSocioRepositorio;
    @Autowired
    private SocioService socioService;
    @Autowired
    private SocioRepositorio socioRepositorio;

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

        //Crear Socio
        Socio socio = new Socio();
        socio.setNombre("Juan");
        socio.setApellidos("Pérez");
        socio.setDni("12345678");
        socio.setTelefono("12345678");
        socio.setCorreo("juan@juan.es");
        socio.setEsActivo(true);
        socioService.guardar(socio);

        Socio socio2 = new Socio();
        socio2.setNombre("Felipe");
        socio2.setApellidos("Rodriguez");
        socio2.setDni("87654321");
        socio2.setTelefono("87654321");
        socio2.setCorreo("felipe@felipe.es");
        socio2.setEsActivo(true);
        socioService.guardar(socio2);

        //Crear AbonoSocio
        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);
        abonoSocio.setAbono(abono1);
        abonoSocio.setFechaInicio(LocalDate.now());
        abonoSocio.setFechaFin(LocalDate.now().plusMonths(1));
        abonoSocio.setPrecio(50.0);
        abonoSocioService.guardar(abonoSocio);

        AbonoSocio abonoSocio2 = new AbonoSocio();
        abonoSocio2.setSocio(socio2);
        abonoSocio2.setAbono(abono1);
        abonoSocio2.setFechaInicio(LocalDate.now());
        abonoSocio2.setFechaFin(LocalDate.now().plusMonths(1));
        abonoSocio2.setPrecio(50.0);
        abonoSocioService.guardar(abonoSocio2);

        //Crear Asistencia
        Asistencia asistencia = new Asistencia();
        asistencia.setSocio(socio);
        asistencia.setFecha(LocalDate.now());
        asistencia.setHoraEntrada(Time.valueOf(LocalTime.now()));
        asistencia.setHoraSalida(Time.valueOf(LocalTime.now().plusHours(1)));
        asistenciaRepositorio.save(asistencia);

        Asistencia asistencia2 = new Asistencia();
        asistencia2.setSocio(socio);
        asistencia2.setFecha(LocalDate.now().plusDays(1));
        asistencia2.setHoraEntrada(Time.valueOf(LocalTime.now()));
        asistencia2.setHoraSalida(Time.valueOf(LocalTime.now().plusHours(1)));
        asistenciaRepositorio.save(asistencia2);



    }

    @Test
    public void testVerAbonosPositivo() {
        //GIVEN
        //WHEN
        List<Abono> abonos = abonoService.getAll();
        //THEN
        assertEquals(4, abonos.size());
    }

    @Test
    public void testVerAbonosNegativo() {
        // GIVEN
        abonoRepositorio.deleteAll();

        // WHEN
        List<Abono> abonos = abonoService.getAll();

        // THEN
        assertNotNull(abonos);
        assertTrue(abonos.isEmpty());
    }

    @Test
    public void testTotalAsistenciaPositivo(){


    }
}

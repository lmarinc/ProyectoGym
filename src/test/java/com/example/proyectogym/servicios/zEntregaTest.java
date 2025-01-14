package com.example.proyectogym.servicios;

import com.example.proyectogym.dto.*;
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
public class zEntregaTest {

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
        abono1.setDuracion(30);
        abonoRepositorio.save(abono1);

        Abono abono2 = new Abono();
        abono2.setNombre("Abono Trimestral");
        abono2.setPrecio(120.0);
        abono2.setTipoAbono(TipoAbono.TRIMESTRAL);
        abono2.setDuracion(90);
        abonoRepositorio.save(abono2);

        Abono abono3 = new Abono();
        abono3.setNombre("Abono Anual");
        abono3.setPrecio(400.0);
        abono3.setTipoAbono(TipoAbono.ANUAL);
        abono3.setDuracion(365);
        abonoRepositorio.save(abono3);

        Abono abono4 = new Abono();
        abono4.setNombre("Abono Semestral");
        abono4.setPrecio(200.0);
        abono4.setTipoAbono(TipoAbono.SEMESTRAL);
        abono4.setDuracion(180);
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
        abonoSocio2.setSocio(socio);
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
    public void testTotalAsistenciaPositivo() {
        // GIVEN
        AsistenciaDto asistenciaDto = new AsistenciaDto();
        asistenciaDto.setSocioId(1);

        // WHEN
        MensajeDTO mensajeDTO = asistenciaService.totalAsistencia(asistenciaDto);

        // THEN
        assertNotNull(mensajeDTO);
        assertEquals("El socio con ID 1 ha asistido un total de 2 días, acumulando 2 horas en el gimnasio.", mensajeDTO.getMensaje());
    }
    @Test
    public void testTotalAsistenciaNegativo() {
        // GIVEN
        AsistenciaDto asistenciaDto = new AsistenciaDto();
        asistenciaDto.setSocioId(3);

        // WHEN
        MensajeDTO mensajeDTO = asistenciaService.totalAsistencia(asistenciaDto);

        // THEN
        assertNotNull(mensajeDTO);
        assertEquals("El socio con ID 3 no existe.", mensajeDTO.getMensaje());
    }

    @Test
    public void testImporteGastadoPositivo() {
        // GIVEN
        Integer socioId = 1;

        // WHEN
        MensajeDTO mensajeDTO = socioService.totalGasto(socioId);

        // THEN
        assertNotNull(mensajeDTO);
        assertEquals("El socio con ID 1 ha gastado un total de 100.0€ en abonos.", mensajeDTO.getMensaje());
    }

    @Test
    public void testImporteGastadoNegativo(){
        // GIVEN
        Integer socioId = 2;

        // WHEN
        MensajeDTO mensajeDTO = socioService.totalGasto(socioId);

        // THEN
        assertNotNull(mensajeDTO);
        assertEquals("El socio no tiene abonos registrados.", mensajeDTO.getMensaje());
    }

    @Test
    public void testRenovarAbonoPositivo(){
        // Given
        RenovarAbonoVigenteDTO dto = new RenovarAbonoVigenteDTO();
        dto.setIdSocio(1);

        //When
        MensajeAbonoSocioDTO mensajeAbonoSocioDTO = abonoService.renovarAbonoVigente(dto);

        //Then
        assertNotNull(mensajeAbonoSocioDTO);
        assertEquals("Abono renovado exitosamente para Juan Pérez", mensajeAbonoSocioDTO.getMensaje());

    }

    @Test
    public void testRenovarAbonoNegativo(){
        // Given
        RenovarAbonoVigenteDTO dto = new RenovarAbonoVigenteDTO();
        dto.setIdSocio(2);

        //When
        MensajeAbonoSocioDTO mensajeAbonoSocioDTO = abonoService.renovarAbonoVigente(dto);

        //Then
        assertNotNull(mensajeAbonoSocioDTO);
        assertEquals("El socio no tiene abonos previos.", mensajeAbonoSocioDTO.getMensaje());

    }

    @Test
    public void testEditarSocioPositivo() {
        // GIVEN
        Socio socio = socioRepositorio.findById(1).orElse(null);
        assertNotNull(socio);

        socio.setNombre("Juan Editado");
        socio.setApellidos("Pérez Editado");
        socio.setTelefono("87654321");
        socio.setCorreo("juaneditado@juan.es");

        // WHEN
        Socio socioActualizado = socioService.guardar(socio);

        // THEN
        assertNotNull(socioActualizado);
        assertEquals("Juan Editado", socioActualizado.getNombre());
        assertEquals("Pérez Editado", socioActualizado.getApellidos());
        assertEquals("12345678", socioActualizado.getDni());
        assertEquals("87654321", socioActualizado.getTelefono());
        assertEquals("juaneditado@juan.es", socioActualizado.getCorreo());
        assertTrue(socioActualizado.getEsActivo());
    }
    @Test
    public void testEditarSocioNegativo() {
        // GIVEN
        Socio socio = socioRepositorio.findById(2).orElse(null);
        assertNotNull(socio);

        socio.setNombre("");

        // WHEN & THEN
        Exception exception = assertThrows(Exception.class, () -> {
            socioService.guardar(socio);
        });

        assertEquals("El nombre no puede estar en blanco.", exception.getMessage());
    }

    @Test
    public void testEliminarSocioPositivo(){
        //Given
        Integer idSocio = 2;

        //When
        MensajeDTO mensajeDTO = socioService.eliminarPorId(idSocio);

        //Then
        assertNotNull(mensajeDTO);
        assertEquals("Socio eliminado", mensajeDTO.getMensaje());

    }

    @Test
    public void testEliminarSocioNegativo(){
        //Given
        Integer idSocio = 1;

        //When
        MensajeDTO mensajeDTO = socioService.eliminarPorId(idSocio);

        //Then
        assertNotNull(mensajeDTO);
        assertEquals("El socio no puede ser eliminado porque tiene un abono vigente.", mensajeDTO.getMensaje());

    }
}

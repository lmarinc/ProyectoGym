package com.example.proyectogym.servicios;

import com.example.proyectogym.dto.MensajeAbonoSocioDTO;
import com.example.proyectogym.dto.MensajeDTO;
import com.example.proyectogym.dto.RenovarAbonoVigenteDTO;
import com.example.proyectogym.modelos.Abono;
import com.example.proyectogym.modelos.AbonoSocio;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AbonoSocioRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class SocioServiceIntegracionTest {

    @InjectMocks
    private SocioService socioService;

    @Mock
    private SocioRepositorio socioRepositorio;

    @Mock
    private AbonoSocioRepositorio abonoSocioRepositorio;

    @Mock
    private AbonoService abonoService;

    @Mock
    private AbonoSocioService abonoSocioService;

    @BeforeEach
    public void setUp() {
        // Initialize any required data here
    }

    @Test
    public void testImporteGastadoPositivo() {
        // GIVEN
        Integer socioId = 1;
        Socio socio = new Socio();
        socio.setId(socioId);
        Mockito.when(socioRepositorio.findById(socioId)).thenReturn(Optional.of(socio));
        Mockito.when(abonoSocioRepositorio.obtenerTotalGastoPorSocio(socioId)).thenReturn(100.0);

        // WHEN
        MensajeDTO mensajeDTO = socioService.totalGasto(socioId);

        // THEN
        assertNotNull(mensajeDTO);
        assertEquals("El socio con ID 1 ha gastado un total de 100.0€ en abonos.", mensajeDTO.getMensaje());
        Mockito.verify(socioRepositorio, Mockito.times(1)).findById(socioId);
        Mockito.verify(abonoSocioRepositorio, Mockito.times(1)).obtenerTotalGastoPorSocio(socioId);
    }

    @Test
    public void testRenovarAbonoPositivo() {
        // Given
        RenovarAbonoVigenteDTO dto = new RenovarAbonoVigenteDTO();
        dto.setIdSocio(1);

        Socio socio = new Socio();
        socio.setId(1);
        socio.setNombre("Juan");
        socio.setApellidos("Pérez");

        AbonoSocio abonoSocio = new AbonoSocio();
        abonoSocio.setSocio(socio);
        abonoSocio.setAbono(new Abono());
        abonoSocio.setFechaInicio(LocalDate.now());
        abonoSocio.setFechaFin(LocalDate.now().plusMonths(1));
        abonoSocio.setPrecio(50.0);

        MensajeAbonoSocioDTO mensajeAbonoSocioDTO = new MensajeAbonoSocioDTO();
        mensajeAbonoSocioDTO.setMensaje("Abono renovado exitosamente para Juan Pérez");
        mensajeAbonoSocioDTO.setNuevoAbonoSocio(abonoSocio);

        when(socioRepositorio.findById(1)).thenReturn(Optional.of(socio));
        when(abonoSocioRepositorio.findFirstBySocioIdOrderByFechaFinDesc(1)).thenReturn(abonoSocio);
        when(abonoSocioService.guardar(Mockito.any(AbonoSocio.class))).thenReturn(abonoSocio);
        when(abonoService.renovarAbonoVigente(dto)).thenCallRealMethod();

        // When
        MensajeAbonoSocioDTO result = abonoService.renovarAbonoVigente(dto);

        // Then
        assertNotNull(result);
        assertEquals("Abono renovado exitosamente para Juan Pérez", result.getMensaje());
        Mockito.verify(socioRepositorio, Mockito.times(1)).findById(1);
        Mockito.verify(abonoSocioRepositorio, Mockito.times(1)).findFirstBySocioIdOrderByFechaFinDesc(1);
        Mockito.verify(abonoSocioService, Mockito.times(1)).guardar(Mockito.any(AbonoSocio.class));
    }

    @Test
    public void testEditarSocioPositivo() {

    }

    @Test
    public void testEliminarSocioPositivo() {

    }
}
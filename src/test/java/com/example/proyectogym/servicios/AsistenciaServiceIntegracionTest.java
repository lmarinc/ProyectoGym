package com.example.proyectogym.servicios;


import com.example.proyectogym.dto.AsistenciaDto;
import com.example.proyectogym.dto.MensajeDTO;
import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.repositorios.AsistenciaRepositorio;
import com.example.proyectogym.repositorios.SocioRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AsistenciaServiceIntegracionTest {

    @InjectMocks
    private AsistenciaService asistenciaService;

    @Mock
    private AsistenciaRepositorio asistenciaRepositorio;

    @Mock
    private SocioService socioService;

    @Mock
    private SocioRepositorio socioRepositorio;


    @Test
    public void testTotalAsistenciaPositivo() {
        // GIVEN
        Integer socioId = 1;
        AsistenciaDto asistenciaDto = new AsistenciaDto();
        asistenciaDto.setSocioId(socioId);

        Socio socio = new Socio();
        socio.setId(socioId);
        socio.setNombre("Juan");
        socio.setApellidos("Pérez");
        socio.setDni("12345678");
        socio.setTelefono("12345678");
        socio.setCorreo("juan@juan.es");
        socio.setEsActivo(true);

        Mockito.when(socioRepositorio.findById(socioId)).thenReturn(Optional.of(socio));
        Mockito.when(asistenciaRepositorio.obtenerTotalDiasAsistencia(socioId)).thenReturn(2);
        Mockito.when(asistenciaRepositorio.obtenerTotalHorasAsistencia(socioId)).thenReturn(2);

        // WHEN
        MensajeDTO mensajeDTO = asistenciaService.totalAsistencia(asistenciaDto);

        // THEN
        assertNotNull(mensajeDTO);
        assertEquals("El socio con ID 1 ha asistido un total de 2 días, acumulando 2 horas en el gimnasio.", mensajeDTO.getMensaje());
        Mockito.verify(socioRepositorio, Mockito.times(1)).findById(socioId);
        Mockito.verify(asistenciaRepositorio, Mockito.times(1)).obtenerTotalDiasAsistencia(socioId);
        Mockito.verify(asistenciaRepositorio, Mockito.times(1)).obtenerTotalHorasAsistencia(socioId);
    }



}

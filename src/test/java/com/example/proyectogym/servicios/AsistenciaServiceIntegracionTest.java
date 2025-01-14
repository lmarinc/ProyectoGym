package com.example.proyectogym.servicios;


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
    public void testTotalAsistenciaIntegracion(){
        //GIVEN
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



}

package com.example.proyectogym;

import com.example.proyectogym.modelos.Asistencia;
import com.example.proyectogym.modelos.Socio;
import com.example.proyectogym.servicios.AsistenciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
public class AsistenciaTest {

    @Autowired
    private AsistenciaService asistenciaService;

    @Test
    void testFindAllAsistencias() {
        asistenciaService.getAll().forEach(asistencia -> System.out.println(asistencia.getFecha()));
    }
    @Test
    void testFindAsistenciaById() {
        System.out.println(asistenciaService.findById(1).getFecha());

    }
    @Test
    void testCreateAsistencia() {
        Asistencia asistencia = new Asistencia();
        asistencia.setFecha(LocalDate.now());
        asistencia.setHoraEntrada(Time.valueOf(LocalTime.now()));
        asistencia.setHoraSalida(Time.valueOf(LocalTime.now()));
        Socio socio = new Socio();
        socio.setId(1);
        asistencia.setSocio(socio);
        asistenciaService.guardar(asistencia);
    }
    @Test
    void testDeleteAsistencia() {
        asistenciaService.eliminar(11);
    }
}

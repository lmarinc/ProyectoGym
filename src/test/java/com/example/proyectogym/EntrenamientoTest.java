package com.example.proyectogym;

import com.example.proyectogym.modelos.Entrenamiento;
import com.example.proyectogym.modelos.Monitor;
import com.example.proyectogym.repositorios.MonitorRepositorio;
import com.example.proyectogym.servicios.EntrenamientoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class EntrenamientoTest {

    @Autowired
    private EntrenamientoService entrenamientoService;
    @Autowired
    private MonitorRepositorio monitorRepositorio;

    @Test
    void testFindAllEntrenamientos() {
        entrenamientoService.getAll().forEach(entrenamiento -> System.out.println(entrenamiento.getNombreEntrenamiento()));
    }

    @Test
    void testFindEntrenamientoById() {
        System.out.println(entrenamientoService.findById(1).getNombreEntrenamiento());
    }

    @Test
    @Transactional
    @Commit
    void testCreateEntrenamiento() {
        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.setNombreEntrenamiento("Entrenamiento de prueba");
        entrenamiento.setFrecuencia(3);
        entrenamiento.setDuracion(60);
        entrenamiento.setNivelDificultad("Alto");
        entrenamiento.setFechaInicio(LocalDate.now());
        entrenamiento.setFechaFin(LocalDateTime.now().plusHours(1).toLocalDate());
        Monitor monitor = monitorRepositorio.findById(1).orElse(null);
        entrenamiento.setMonitor(monitor);
        entrenamientoService.guardar(entrenamiento);
    }
    @Test
    void testUpdateEntrenamiento() {
        Entrenamiento entrenamiento = entrenamientoService.findById(12);
        entrenamiento.setNombreEntrenamiento("Entrenamiento de prueba modificado");
        entrenamientoService.guardar(entrenamiento);
    }

//    @Test
//    void testDeleteEntrenamiento() {
//        entrenamientoService.eliminar(12);
//    }

}

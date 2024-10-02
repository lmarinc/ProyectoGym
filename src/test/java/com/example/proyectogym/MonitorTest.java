package com.example.proyectogym;

import com.example.proyectogym.modelos.Monitor;
import com.example.proyectogym.servicios.MonitorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonitorTest {

    @Autowired
    private MonitorService monitorService;

    @Test
    void testFindAllMonitores() {
        monitorService.getAll().forEach(monitor -> System.out.println(monitor.getNombre()));
    }
    @Test
    void testFindMonitorById() {
        System.out.println(monitorService.findById(1).getNombre());
    }
    @Test
    void testCreateMonitor() {
        Monitor monitor = new Monitor();
        monitor.setNombre("Juan");
        monitor.setDni("1234567A");
        monitor.setSalario(1000.0);
        monitorService.guardar(monitor);
    }
    @Test
    void testDeleteMonitor() {
        monitorService.eliminar(11);
    }

}

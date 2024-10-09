package com.example.proyectogym.controladores;


import com.example.proyectogym.modelos.Monitor;
import com.example.proyectogym.servicios.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/listar")
    public List<Monitor> getAllMonitores() {
        List<Monitor> monitores = monitorService.getAll();
        return monitores;
    }
    @GetMapping()
    public Monitor getById(@RequestParam Integer id) {
        Monitor monitor = monitorService.findById(id);
        return monitor;
    }

    @PostMapping()
    public Monitor guardar(@RequestBody Monitor monitor) {
        Monitor monitorGuardado = monitorService.guardar(monitor);
        return monitorGuardado;
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return monitorService.eliminarPorId(id);
    }
}

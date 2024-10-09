package com.example.proyectogym.controladores;

import com.example.proyectogym.modelos.Entrenamiento;
import com.example.proyectogym.servicios.EntrenamientoService;
import com.example.proyectogym.servicios.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrenamiento")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoService entrenamientoService;

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/listar")
    public List<Entrenamiento> getAllEntrenamientos() {
        List<Entrenamiento> entrenamientos = entrenamientoService.getAll();
        return entrenamientos;
    }
    @GetMapping()
    public Entrenamiento getById(@RequestParam Integer id) {
        Entrenamiento entrenamiento = entrenamientoService.findById(id);
        return entrenamiento;
    }
    @PostMapping
    public Entrenamiento guardar(@RequestBody Entrenamiento entrenamiento) {
        Entrenamiento entrenamientoGuardado = entrenamientoService.guardar(entrenamiento);
        return entrenamientoGuardado;
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return entrenamientoService.eliminarPorId(id);
    }

}
